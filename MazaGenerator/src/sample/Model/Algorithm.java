package sample.Model;

public abstract class Algorithm {
    protected Problem problem;

    public Algorithm(Problem problem) {
        if(problem == null) {
            throw new IllegalArgumentException("Invalid problem!");
        }
        else
            this.problem = problem;
    }

    public abstract void solve();
}
