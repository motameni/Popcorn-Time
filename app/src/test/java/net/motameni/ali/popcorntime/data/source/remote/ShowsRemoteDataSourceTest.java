package net.motameni.ali.popcorntime.data.source.remote;

import net.motameni.ali.popcorntime.data.ShowsDataSource;
import net.motameni.ali.popcorntime.data.source.Show;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * PopcornTime
 * Created by ali on 2017.
 */
public class ShowsRemoteDataSourceTest {
    @Test
    public void getInstance() throws Exception {
        ShowsRemoteDataSource dataSource = ShowsRemoteDataSource.getInstance();
        assertEquals(dataSource == null, false);
    }

    @Test
    public void getShows() throws Exception {
        ShowsRemoteDataSource dataSource = ShowsRemoteDataSource.getInstance();
        dataSource.getShows(new ShowsDataSource.GetShowsCallBack() {
            @Override
            public void onShowsLoaded(List<Show> shows) {
                Show show = shows.get(0);
                assertEquals(show.getId(), 1);
                assertEquals(show.getName(), "Under the Dome");
                assertEquals(show.getSummary(), "<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>");
                assertEquals(show.getRuntime(), 60);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(show.getPremiered());
                assertEquals(calendar.get(Calendar.YEAR), 2013);
                assertEquals("Rate difference", show.getRate(), 6.5, 0.0);
                assertEquals(show.getMediumImage(), "http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg");
                assertEquals(show.getOriginalImage(), "http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg");
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Test
    public void getShow() throws Exception {
        ShowsRemoteDataSource dataSource = ShowsRemoteDataSource.getInstance();
        dataSource.getShow(1, new ShowsDataSource.GetShowCallBack() {
            @Override
            public void onShowLoaded(Show show) {
                assertEquals(show.getId(), 1);
                assertEquals(show.getName(), "Under the Dome");
                assertEquals(show.getSummary(), "<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>");
                assertEquals(show.getRuntime(), 60);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(show.getPremiered());
                assertEquals(calendar.get(Calendar.YEAR), 2013);
                assertEquals("Rate difference", show.getRate(), 6.5, 0.0);
                assertEquals(show.getMediumImage(), "http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg");
                assertEquals(show.getOriginalImage(), "http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg");
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

}