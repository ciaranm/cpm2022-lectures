/* vim: set sw=4 sts=4 et foldmethod=syntax : */

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.constraints.IIntConstraintFactory.*;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.exception.ContradictionException;

public class ScalarEquality
{
    public static void main(String args[]) throws ContradictionException, FileNotFoundException
    {
        int confusification = 4;

        Model model = new Model("Scalar");
        Solver solver = model.getSolver();

        IntVar[] x = new IntVar[confusification];
        for (int i = 0 ; i < confusification ; ++i)
            x[i] = model.intVar("x" + Integer.toString(i), new int[] { 0, 2 });

        int[] c = new int[confusification];
        Arrays.fill(c, 1);

        model.scalar(x, c, "=", confusification / 4 * 2 + 1).post();

        System.out.println(model);

        solver.propagate();

        System.out.println(model);
    }
}
