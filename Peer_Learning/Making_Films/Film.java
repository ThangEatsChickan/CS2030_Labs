import java.util.ArrayList;

class Film {
    public final String title;
    public final double imdbscore;
    public final double rottenscore;
    public final Director dir;
    public Cast[] cast;
 
    public Film(String title, double imdbscore, double rottenscore, Director dir, Cast... casts) {
        this.title = title;
        this.imdbscore = imdbscore;
        this.rottenscore = rottenscore;
        this.dir = dir;
        int i = 0;
        for(Cast cast: casts) {
            cast
        }
     }
}
        
