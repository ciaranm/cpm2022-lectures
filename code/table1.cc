// check whether selectable tuples are still feasible
state.for_each_value_while(table.selector, [&](Integer tuple_idx) -> bool {
  bool is_feasible = true;
  for (unsigned idx = 0; idx < table.vars.size(); ++idx)
    if (! feasible(state, table.vars[idx], tuples[tuple_idx.raw_value][idx])) {
      is_feasible = false;
      break;
    }

  if (! is_feasible) {
    switch (state.infer(table.selector != Integer(tuple_idx), NoJustificationNeeded{})) {
    case Inference::NoChange: break;
    case Inference::Change: changed = true; break;
    case Inference::Contradiction: contradiction = true; break;
    }
  }

  return ! contradiction;
});
