**** OTHER COLLABORATORS ****

1.  <a href="https://github.com/krock5746">Kevin Scruggs</a>

2.  <a href="https://github.com/NehaRege">Neha Rege</a>

3.  <a href="https://github.com/jonkaman">Jon Kim</a>


**** APP INFO ****

"FitToPrint" is a newsfeed app which calls Bing's API for trending and categorical news articles.



It allows a user to:

	•Scroll amongst trending news determined by Bing.

	•Click on a trending news article to view Bing's search results related to that news topic in a webview.

	•Choose a particular news category in a side navigation drawer (e.g. Health, Sports, Politics, etc.)

	•Click on a particular category's article to directly read the article in a webview.

	•Click on a heart in the upper right hand corner to follow preferred news categories.

	•Use search to query Bing's API for news articles that're relevant to the search term.



**** BUGS ****

	•Although layout files have been made for screen orientation, they aren't displayed on orientation change. If you're viewing a single article and then rotate the screen, you're brought back to the list of trending articles.
	
	•Lifecycle of daily notification job might not be managed properly. Not ending it?

	•Internet connectivity is required to display any articles. We haven't implemented database storage for offline caching. 
	




