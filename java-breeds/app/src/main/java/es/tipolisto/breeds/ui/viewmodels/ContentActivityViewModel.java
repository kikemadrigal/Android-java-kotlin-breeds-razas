package es.tipolisto.breeds.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import es.tipolisto.breeds.data.model.BreedsDog;
import es.tipolisto.breeds.data.model.Cat;

public class ContentActivityViewModel extends ViewModel {
    private int lives;
    private int score;
    private Cat cat;
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

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public BreedsDog getBreedsDog() {
        return breedsDog;
    }

    public void setBreedsDog(BreedsDog breedsDog) {
        this.breedsDog = breedsDog;
    }
}
