/*
 * @(#)Mp3IsoTag.java  1.0 06/03/2013
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

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;

/**
 * The <code>Mp3IsoTag</code> class is a custom tag 
 * for handling capitalization of  given attributes strings.
 * Here is an example for the custom tag operation:
 * <blockquote><pre>
 *     november rain -> November Rain
 * </pre></blockquote>
 * @author  Ido Gold
 * @author  Sahar Rehani
 * @version 1.0, 06/03/2013
 */
public class Mp3IsoTag extends SimpleTagSupport
{
	private String artist;	//
	private String title;	//

	/*
	 * 
	 */
	public void setArtist(String str)
	{
		artist = mp3ISO(str);
	}

	/*
	 * 
	 */
	public void setTitle(String str)
	{
		title = mp3ISO(str);
	}

	/*
	 * 
	 */
	public String mp3ISO(String str)
	{
		String returnStr = "";
		String tmp = "";
		String sentence[] = str.split(" ");

		for (int i=0; i < sentence.length; i++)
		{
			tmp = sentence[i];
			tmp = tmp.toLowerCase();
			Character.toUpperCase(tmp.charAt(0)); 
			returnStr += tmp + " ";
		}
		return returnStr;
	}

	public void doTag() throws JspException, IOException 
	{
		JspWriter out = getJspContext().getOut();
		out.print(artist + "- " + title);
	}
}