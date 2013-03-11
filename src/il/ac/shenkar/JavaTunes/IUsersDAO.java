/*
 * @(#)IUsersDAO.java  1.0 06/03/2013
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

import java.util.HashMap;

/**
 * The <code>IUsersDAO</code> class acts as an interface for the UsersDAO.
 * @author  Ido Gold
 * @author  Sahar Rehani
 * @version 1.0, 06/03/2013
 */
public interface IUsersDAO 
{
	/*
	 * Adds a new user to the application 
	 * @param ob - user to add
	 * @return true if succeeded, false otherwise
	 */
	public abstract boolean addUser(User ob);
	
	/*
	 * Deletes a user from the application 
	 * @param ob - user to delete
	 * @return true if succeeded, false otherwise
	 */
	public abstract boolean deleteUser(User ob);
	
	/*
	 * Get all registered users
	 * @return all users
	 */
	public abstract HashMap<String,User> getUsers();
	
	/*
	 * Updates a specific user field data in the database
	 * @param field - field to update
	 * @param user - user's field to update
	 * @param content - new field content
	 * @return true if succeeded, false otherwise
	 */
	public boolean updateUserField(String field, String user, String content);
}
