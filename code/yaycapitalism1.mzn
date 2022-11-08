include "globals.mzn";

enum Shifts = { Day, Night, Off };
enum Days = anon_enum(14);
enum Workers = anon_enum(11);

int: day_cover = 4;
int: night_cover = 2;

array[Days, Workers] of var Shifts: allocation;

constraint forall (d in Days)
    (sum(w in Workers)(allocation[d, w] == Day) = day_cover);
constraint forall (d in Days)
    (sum(w in Workers)(allocation[d, w] == Night) = night_cover);

constraint forall (w in Workers)(regular([allocation[d, w] | d in Days],
    "(Off|(Day{1,5} Off)|(Night{1,2} Off))* (Day{1,5}|Night{1,2}|Off)"));

solve ::int_search([allocation[d, w] | d in Days, w in Workers],
    first_fail, indomain_min) satisfy;

output [ join(" ", [
        format_justify_string(-5, show(allocation[d, w])) | d in Days
    ]) ++ "\n" | w in Workers];
