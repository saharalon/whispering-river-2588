/*
 * @(#)UserDAO.java  1.0 06/03/2013
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * The <code>UsersDAO</code> class is a data access object
 * that manages users in the application.
 * @author  Ido Gold
 * @author  Sahar Rehani
 * @version 1.0, 06/03/2013
 */
public class UsersDAO implements IUsersDAO
{		
	private SessionFactory factory;

	private UsersDAO() 
	{	
		//creating factory for getting sessions
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	private static class SingletonHolder
	{ 
		private static final UsersDAO INSTANCE = new UsersDAO();
	}
	
	/*
	 * Returns the instance of the singleton object
	 */
	public static UsersDAO getInstance() 
	{
		return SingletonHolder.INSTANCE;
	}

	public boolean updateUserField(String field, String user, String content)
	{

		Session session = factory.openSession();
		session.beginTransaction();

		User obj = (User) session.get(User.class, user);

		switch (field) 
		{

		case "cart":		obj.setCart(content);
		break;
		case "purchased":	obj.setPurchasedTracks(content);
		break;
		case "playlist":	obj.setPlaylist(content);
		break;
		default: break;

		}

		session.getTransaction().commit();
		session.close();

		return true;
	}

	public boolean addUser(User ob) 
	{		
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(ob);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public boolean deleteUser(User ob) 
	{ 
		Session session = factory.openSession();
		session.beginTransaction();
		session.delete(ob);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@SuppressWarnings({ "unchecked" })
	public HashMap<String,User> getUsers()
	{ 
		List<User> tmpList = new ArrayList<User>();

		Session session = factory.openSession();
		session.beginTransaction();
		tmpList = session.createQuery("from User").list();

		HashMap<String, User> usersMap = new HashMap<String, User>();
		
		for (User user : tmpList) 
		{
			usersMap.put(user.getUsername(), user);
		}

		session.close(); 
		return usersMap;
	}
}