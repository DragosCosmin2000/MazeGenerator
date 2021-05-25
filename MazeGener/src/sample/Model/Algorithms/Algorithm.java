package sample.Model.Algorithms;

import sample.Model.Problem;

public abstract class Algorithm {
    protected Problem problem;

    public Algorithm(Problem problem) {
        if(problem == null) {
            throw new IllegalArgumentException("Invalid problem!");
        }
        else {
            this.problem = problem;
        }
    }

    public Problem getProblem() {
        return problem;
    }

    // Given the current state of the problem, we can make a transition to another state.
    public abstract void transition();
}