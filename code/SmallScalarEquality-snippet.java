Model model = new Model("ScalarEquality");
Solver solver = model.getSolver();

IntVar x1 = model.intVar("x1", 2, 5);
IntVar x2 = model.intVar("x2", 2, 5);
IntVar x3 = model.intVar("x3", 2, 5);

model.scalar(
        new IntVar[] { x1, x2, x3 },
        new int[]    {  2, -2,  4 },
        "=", 11).post();

System.out.println(model);

solver.propagate();

System.out.println(model);
