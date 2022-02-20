package es.tipolisto.breeds.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.data.model.CatSimple;

public class ContentActivityViewModel extends ViewModel {
    private int lives;
    private int score;
    private CatSimple cat;
    private BreedsDog breedsDog;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public CatSimple getCat() {
        return cat;
    }

    public void setCat(CatSimple cat) {
        this.cat = cat;
    }

    public BreedsDog getBreedsDog() {
        return breedsDog;
    }

    public void setBreedsDog(BreedsDog breedsDog) {
        this.breedsDog = breedsDog;
    }
}
