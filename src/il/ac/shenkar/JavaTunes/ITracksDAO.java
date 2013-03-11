/*
 * @(#)ITracksDAO.java  1.0 06/03/2013
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

import java.util.Collection;

/**
 * The <code>ITracksDAO</code> class acts as an interface for the TracksDAO.
 * @author  Ido Gold
 * @author  Sahar Rehani
 * @version 1.0, 06/03/2013
 */
public interface ITracksDAO
{
	/*
	 * Adds a track to the tracks pool
	 * @param ob - track to add
	 * @return true if succeeded, false otherwise
	 */
	public abstract boolean addTrack(Track ob);
	
	/*
	 * Deletes a track from the tracks pool
	 * @param ob - track to delete
	 * @return true if succeeded, false otherwise
	 */
	public abstract boolean deleteTrack(Track ob);
	
	/*
	 * Get all available tracks
	 * @return all tracks
	 */
	public abstract Collection<Track> getTracks(String list);			
}
