package sample.Model.Algorithms;

import sample.Model.Structures._3D.Problem;

public abstract class Algorithm3D {
    protected Problem problem;

    public Algorithm3D(Problem problem) {
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

