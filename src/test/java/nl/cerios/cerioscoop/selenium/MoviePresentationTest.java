package nl.cerios.cerioscoop.selenium;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.selenium.pages.IndexPage;
import nl.cerios.cerioscoop.selenium.pages.MoviePage;
import nl.cerios.testutil.SeleniumTest;

public class MoviePresentationTest extends SeleniumTest {
	
	@Test
	public void shouldNavigateToMoviePresentationOfTheLegendOfTarzan() {
	    MoviePage moviePage = new IndexPage(getWebDriver())
	      .navigateToMoviePresentation("1");
	    
	    Assert.assertNotNull(moviePage);
	    Assert.assertEquals("MovieTitle: The Legend of Tarzan", moviePage.getMovieTitleFromMoviePage());
	    Assert.assertEquals("Description: A story about a boy that survived in the jungle.", moviePage.getMovieDescriptionFromMoviePage());
	  }
	
	@Test
	public void shouldNavigateToMoviePresentationOfSnatch() {
	    MoviePage moviePage = new IndexPage(getWebDriver())
	      .navigateToMoviePresentation("7");
	    
	    Assert.assertNotNull(moviePage);
	    Assert.assertEquals("MovieTitle: Snatch", moviePage.getMovieTitleFromMoviePage());
	    Assert.assertEquals("Description: A story about a diamond that all the pikeys want to have.", moviePage.getMovieDescriptionFromMoviePage());
	  }
}
