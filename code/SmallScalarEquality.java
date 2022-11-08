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

public class SmallScalarEquality
{
    public static void main(String args[]) throws ContradictionException, FileNotFoundException
    {
        Model model = new Model("ScalarEquality");
        Solver solver = model.getSolver();

        IntVar x1 = model.intVar("x1", 2, 5);
        IntVar x2 = model.intVar("x2", 2, 5);
        IntVar x3 = model.intVar("x3", 2, 5);

        model.scalar(new IntVar[] { x1, x2, x3 }, new int[] { 2, -2, 4 }, "=", 11).post();

        System.out.println(model);

        solver.propagate();

        System.out.println(model);
    }
}
