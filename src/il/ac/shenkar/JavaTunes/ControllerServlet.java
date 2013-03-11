/*
 * @(#)ControllerServlet.java  1.0 06/03/2013
 *
 * Copyright (C) 2013 Ido Gold & Sahar Rehani
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 */

package il.ac.shenkar.JavaTunes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * The <code>ControllerServlet</code> class 
 * represents the apllication's MVC.
 * It acts as a controller for the web application
 * @author  Ido Gold
 * @author  Sahar Rehani
 * @version 1.0, 06/03/2013
 */
public class ControllerServlet extends HttpServlet
{
	private static final long serialVersionUID = -1006196327708157975L;
	static Logger logger = Logger.getLogger(
			il.ac.shenkar.JavaTunes.ControllerServlet.class.getName());
	
	/*
	 * When intercepts HTTP post requests
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response)

    throws ServletException, IOException, NullPointerException
    {
    	BasicConfigurator.resetConfiguration();
    	BasicConfigurator.configure();
    	logger.info("POST request received");
    	requestsHandler(request, response);
    }
    
    /*
	 * When intercepts HTTP get requests
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)

    throws ServletException, IOException, NullPointerException
    {
    	BasicConfigurator.resetConfiguration();
    	BasicConfigurator.configure();
    	logger.info("GET request received");
    	
    	requestsHandler(request, response);
    }
    
    /*
     * Acts as the servlet's controller
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws NullPointerException
     */
    public void requestsHandler(HttpServletRequest request, HttpServletResponse response)
    
    throws ServletException, IOException, NullPointerException
    {	
    	RequestDispatcher dispatcher = null;
    	String dispatchTo = "/jsp/store.jsp";
    	
    	logger.info("initialization: (1) Dispatcher (2) Session Factory (3) Users DAO (4) Tracks DAO (5) Response content type");
		
		//creating factory for getting sessions
		SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
		//creating a new session for adding products
		Session session = factory.openSession();
		session.beginTransaction();
		session.getTransaction().commit();
		
		IUsersDAO usersDAO;
		usersDAO = UsersDAO.getInstance();
		
		ITracksDAO tracksDAO;
		tracksDAO = TracksDAO.getInstance();
		
		response.setContentType("text/html");
		
		HttpSession userSession = request.getSession();
		
		logger.warn("'usrField' is used throughout the servlet, yet it could be null and some methods might throw exception when accessing it");
		String usrField = request.getParameter("username");
		if (usrField == null) { usrField = ""; }
		
		logger.warn("'refer' is used throughout the servlet, yet it could be null and some methods might throw exception when accessing it");
		String refer = request.getParameter("refer");
		if (refer == null) { refer = ""; }
		
		logger.warn("'action' is used throughout the servlet, yet it could be null and some methods might throw exception when accessing it");
		String action = request.getParameter("action");
		if (action == null) { action = ""; }
		
		logger.warn("'id' is used throughout the servlet, yet it could be null and some methods might throw exception when accessing it");
		String id = request.getParameter("id");
		if (id == null) { id = ""; }
		
		HashMap<String,User> users = new HashMap<String,User>(usersDAO.getUsers());
		
		if (users.containsKey(usrField))
		{ 
			
			logger.info("Checking where the request came from (Store views or Log-in page)");
			
			if (refer.equals("login"))	// login success (user found)
			{	
				if (users.get(usrField).getPassword().equals(md5(request.getParameter("password_field"))))
				{
					logger.info("The user: ( " + usrField + ") was found in the DB and his password checks out.");
					Cookie cookie = new Cookie("usercookie",usrField);
					cookie.setMaxAge(7*24*60*60);
					response.addCookie(cookie);
					logger.info("A cookie for user ( " + usrField + ") was added");
				}
				else 
				{						// login failure (user wasn't found)
					logger.info("The user: ( " + usrField + ") was NOT found in the DB (Bad username or password)");
					dispatchTo = "/index.jsp?error=incorrect username / password";
				}
			}
			else if (refer.equals("catalog"))
			{
				logger.info("The request came from 'catalog.jsp'");
				logger.info("A request to add a tune to the Tunes Cart");
				// catalog requesting to add a tune to the cart
				if ((!users.get(usrField).getCart().contains(":" + id)) && (!users.get(usrField).getPurchasedTracks().contains(":" + id)))
				{
					usersDAO.updateUserField("cart",usrField,users.get(usrField).getCart() + ":" + id);
					users.get(usrField).setCart(users.get(usrField).getCart() + ":" + id);
					request.setAttribute("message", "track added successfully");
					logger.info("Tune id ( " + id + ") was added to the Tunes Cart");
				}
				else 
				{
					request.setAttribute("message", "this track already exists in the TunesCart/Purchased Tunes");
					logger.info("Tune id ( " + id + ") already exists in the Tunes Cart");
				}
				request.setAttribute("refer", "/jsp/catalog.jsp");
			}
			
			else if (refer.equals("purchased"))
			{
				logger.info("The request came from 'purchased.jsp'");
				// purchased list: 2 functions: (1) delete a tune, (2) add a tune to playlist
				if (action.equals("addToPlaylist"))
				{
					logger.info("A request to add a tune to the Playlist");
					if (!users.get(usrField).getPlaylist().contains(":" + id))
					{
						usersDAO.updateUserField("playlist",usrField,users.get(usrField).getPlaylist() + ":" + id);
						users.get(usrField).setPlaylist(users.get(usrField).getPlaylist() + ":" + id);
						logger.info("The tune id (" + id + ") was added to the Playlist");
					}
					else
					{
						logger.info("The tune id ( " + id + ") already exists in the Playlist");
						request.setAttribute("message", "this track already exists in the Playlist");
					}
					request.setAttribute("refer", "/jsp/purchased.jsp");
				}
				else if (action.equals("eraseTune"))
				{
					logger.info("A request to erase a tune from the store (completely)");
					String remStr1 = users.get(usrField).getPurchasedTracks();
					remStr1 = remStr1.replace(":" + id, "");
					users.get(usrField).setPurchasedTracks(remStr1);
					usersDAO.updateUserField("purchased", usrField, remStr1);
					remStr1 = users.get(usrField).getPlaylist();
					remStr1 = remStr1.replace(":" + id, "");
					users.get(usrField).setPlaylist(remStr1);
					usersDAO.updateUserField("playlist", usrField, remStr1);
					request.setAttribute("refer", "/jsp/purchased.jsp");
					logger.info("The tune was successfully erased from the DB (playlist,purchased)");
				}
			}
			
			else if (refer.equals("playlist"))
			{
				logger.info("The request came from 'playlist.jsp'");
				// remove tune from playlist
				String remStr2 = users.get(usrField).getPlaylist();
				remStr2 = remStr2.replace(":" + id, "");
				users.get(usrField).setPlaylist(remStr2);
				usersDAO.updateUserField("playlist", usrField, remStr2);
				request.setAttribute("refer", "/jsp/playlist.jsp");
			}
			
			else if (refer.equals("cart"))
			{
				logger.info("The request came from 'cart.jsp'");
				// 2 functions: (1)buy tunes -> add to purchased (2) remove tune from cart
				if (action.equals("removeTune"))
				{
					logger.info("A request to remove a tune from the Tunes Cart");
					String remStr3 = users.get(usrField).getCart();
					remStr3 = remStr3.replace(":" + id, "");
					users.get(usrField).setCart(remStr3);
					usersDAO.updateUserField("cart", usrField, remStr3);
					request.setAttribute("refer", "/jsp/cart.jsp");
					logger.info("The tune id (" + id + ") was removed from the Tunes Cart");
				}
				else if (action.equals("proceed"))
				{
					logger.info("A request to purchase all Tunes in the TunesCart ");
					users.get(usrField).setCart("");
					usersDAO.updateUserField("cart",usrField,"");
					usersDAO.updateUserField("purchased",usrField,users.get(usrField).getPurchasedTracks() + id);
					users.get(usrField).setPurchasedTracks(users.get(usrField).getPurchasedTracks() + id);
					request.setAttribute("refer", "/jsp/thanku.jsp");
					logger.info("Tunes id(s) [" + id + "] were successfully purchased");
				}
			}
			
			else if (refer.equals("player"))
			{
				// currently not in use
			}
			
			else if (refer.equals("logout"))
			{
				logger.info("user ( " + usrField + ") logged out / closed browser tab");
				userSession.invalidate();
				logger.info("user ( " + usrField + ")'s session has been invalidated");
				dispatchTo = "/index.jsp";
				request.setAttribute("error", "");
			}
			
			request.setAttribute("beanUser", users.get(usrField));
			logger.info("There are " + users.size() + " user(s) in the DB");
			
			List<Track> tracks = new ArrayList<Track>(tracksDAO.getTracks("catalog"));
			request.setAttribute("sTracks", tracks);
			logger.info("There are " + tracks.size() + " track(s) in the DB");
			
			String purchasedTracksStr = users.get(usrField).getPurchasedTracks();
			List<Track> purchasedTracks = new ArrayList<Track>(tracksDAO.getTracks(purchasedTracksStr));
			logger.info("User (" + usrField + ") has " + purchasedTracks.size() + " purchased track(s) in the DB");
			
			String playlistStr = users.get(usrField).getPlaylist();
			List<Track> playlist = new ArrayList<Track>(tracksDAO.getTracks(playlistStr));
			logger.info("User (" + usrField + ") has " + playlist.size() + " track(s) in his playlist (DB)");
			
			String cartStr = users.get(usrField).getCart();
			List<Track> cart = new ArrayList<Track>(tracksDAO.getTracks(cartStr));
			logger.info("User (" + usrField + ") has " + cart.size() + " track(s) in his Tunes Cart (DB)");
			
			request.setAttribute("sPurchasedTracks", purchasedTracks);
			request.setAttribute("sPlaylist", playlist);
			request.setAttribute("sCart", cart);
					
			dispatcher = getServletContext().getRequestDispatcher(dispatchTo);
			dispatcher.forward(request,response);
		}
    }
    
    public static String md5(String input) {
        
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
}