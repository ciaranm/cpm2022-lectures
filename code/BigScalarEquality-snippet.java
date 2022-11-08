Model model = new Model("ScalarEquality");
Solver solver = model.getSolver();

IntVar x1 = model.intVar("x1", new int[] { 0, 2, 10 });
IntVar x2 = model.intVar("x2", new int[] { 0, 2, 10 });
IntVar x3 = model.intVar("x3", new int[] { 0, 2, 10 });
IntVar x4 = model.intVar("x4", new int[] { 0, 2, 10 });

model.scalar(
        new IntVar[] { x1, x2, x3, x4 },
        new int[]    {  1,  1,  1,  1 },
        "=", 5).post();

System.out.println(model);

solver.propagate();

System.out.println(model);
