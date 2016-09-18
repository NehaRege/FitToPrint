


![alt tag](https://cloud.githubusercontent.com/assets/4394910/17466917/0a6e2352-5ccd-11e6-8f9d-a19af9724396.gif)

<h3>Other Developers In This Group Project</h3>
<ul>
<li><a href="https://github.com/krock5746">Kevin Scruggs</a></li>
<li><a href="https://github.com/NehaRege">Neha Rege</a></li>		
<li><a href="https://github.com/jkim24">Jon Kim</a></li>
</ul>


<h3>App Info</h3>

"FitToPrint" is a newsfeed app which calls Bing's API for trending and categorical news articles.

It allows a user to:
<ul>
<li>Scroll amongst trending news determined by Bing.</li>
<li>Click on a trending news article to view Bing's search results related to that news topic in a webview.</li>
<li>Choose a particular news category in a side navigation drawer (e.g. Health, Sports, Politics, etc.)</li>
<li>Click on a particular category's article to directly read the article in a webview.</li>
<li>Click on a heart in the upper right hand corner to follow preferred news categories.</li>
<li>Use search to query Bing's API for news articles that're relevant to the search term.</li>
</ul>

<h3>Bugs</h3>
<ul>
<li>Although layout files have been made for screen orientation, they aren't displayed on orientation change. If you're viewing a single article and then rotate the screen, you're brought back to the list of trending articles.</li>
<li>Lifecycle of daily notification job might not be managed properly. Not ending it?</li>
<li>Internet connectivity is required to display any articles. We haven't implemented database storage for offline caching.</li>
</ul>




