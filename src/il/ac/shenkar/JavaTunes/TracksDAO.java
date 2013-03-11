/*
 * @(#)TracksDAO.java  1.0 06/03/2013
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * The <code>TracksDAO</code> class is a data access object
 * that manages tracks in the application.
 * @author  Ido Gold
 * @author  Sahar Rehani
 * @version 1.0, 06/03/2013
 */
public class TracksDAO implements ITracksDAO
{
	private SessionFactory factory;

	private TracksDAO() 
	{	
		//creating factory for getting sessions
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	private static class SingletonHolder
	{ 
		private static final TracksDAO INSTANCE = new TracksDAO();
	}

	/*
	 * Returns the singleton object
	 */
	public static TracksDAO getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	public boolean addTrack(Track ob) 
	{		
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(ob);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public boolean deleteTrack(Track ob) 
	{ 
		Session session = factory.openSession();
		session.beginTransaction();
		session.delete(ob);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<Track> getTracks(String list)
	{ 
		List<Track> tmpList = new ArrayList<Track>();

		Session session = factory.openSession();
		session.beginTransaction();
		tmpList = session.createQuery("from Track").list();
		session.close(); 

		if (list.equals("catalog")) 
		{
			return tmpList;
		}

		HashMap map = new HashMap<String,Track>();
		
		for (Track track : tmpList) map.put(track.getId(), track);
		
		List<Track> tmpList2 = new ArrayList<Track>();
		String tmpStr[] = list.split(":");

		for (int i=0; i < (tmpStr.length - 1); i++)
		{
			Track tmpTrack = (Track)map.get(tmpStr[i+1]);
			tmpList2.add(tmpTrack);
		}

		return tmpList2;
	}
}