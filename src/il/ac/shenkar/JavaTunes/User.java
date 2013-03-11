/*
 * @(#)User.java  1.0 06/03/2013
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

import java.io.Serializable;
import javax.persistence.Column;

/**
 * The <code>User</code> class represents a user in the application.
 * A user has the following properties:
 * username, password, cart, purchasedTracks and playlist
 * @author  Ido Gold
 * @author  Sahar Rehani
 * @version 1.0, 06/03/2013
 */
public class User implements Serializable
{
	private static final long serialVersionUID = -4629805076677240483L;
	
	private String	username;			// user's user name
	private String	password;			// user's password
	private String	cart;				// user's cart content
	private String	purchasedTracks;	// user's list of purchased tracks
	private String	playlist;			// user's play list
	
	public User(String username, String password, String cart, String purchasedTracks,
			String playlist)
	{
		super();
		setUsername(username);
		setPassword(password);
		setCart(cart);
		setPurchasedTracks(purchasedTracks);
		setPlaylist(playlist);
	}
	
	public User()
	{
		setUsername("");
		setPassword("");
		setCart(":");
		setPurchasedTracks(":");
		setPlaylist(":");
	}

	@Column(name="USERNAME", nullable=false)
	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	@Column(name="PASSWORD", nullable=false)
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Column(name="CART", nullable=false)
	public String getCart()
	{
		return cart;
	}
	
	public void setCart(String cart)
	{
		this.cart = cart;
	}
	
	@Column(name="PURCHASED_TRACKS", nullable=false)
	public String getPurchasedTracks()
	{
		return purchasedTracks;
	}

	public void setPurchasedTracks(String purchasedTracks)
	{
		this.purchasedTracks = purchasedTracks;
	}

	@Column(name="PLAYLIST", nullable=false)
	public String getPlaylist() 
	{
		return playlist;
	}

	public void setPlaylist(String playlist)
	{
		this.playlist = playlist;
	}
}