/*
 * @(#)Track.java  1.0 06/03/2013
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
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The <code>Track</code> class represents a track in the application.
 * A track has the following properties:
 * id, artist, title, preLink, downLink and price
 * @author  Ido Gold
 * @author  Sahar Rehani
 * @version 1.0, 06/03/2013
 */
@Entity
@Table(name="tracks")
public class Track implements Serializable
{
	private static final long serialVersionUID = -5121641967804893377L;

	private String 	id;			// unique id number
	private String 	artist;		// track's artist name
	private String 	title;		// track's title
	private String 	preLink;	// preview link
	private String 	downLink;	// download link
	private float	price;		// track's price
	
	public Track(String id, String artist, String title, String preLink,
			String downLink, float price)
	{
		super();
		setId(id);
		setArtist(artist);
		setTitle(title);
		setPreLink(preLink);
		setDownLink(downLink);
		setPrice(price);
	}
	
	public Track() 
	{
		setId("");
		setArtist("");
		setTitle("");
		setPreLink("");
		setDownLink("");
		setPrice(0);
	}
	
	@Column(name="ID", nullable=false)
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Column(name="ARTIST", nullable=false)
	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	@Column(name="TITLE", nullable=false)
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	@Column(name="PRELINK", nullable=false)
	public String getPreLink()
	{
		return preLink;
	}

	public void setPreLink(String preLink)
	{
		this.preLink = preLink;
	}

	@Column(name="DOWNLINK", nullable=false)
	public String getDownLink()
	{
		return downLink;
	}

	public void setDownLink(String downLink)
	{
		this.downLink = downLink;
	}

	@Column(name="PRICE", nullable=false)
	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price) 
	{
		this.price = price;
	}
}