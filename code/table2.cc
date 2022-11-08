// check for supports in selectable tuples
for (unsigned idx = 0; idx < table.vars.size() && ! contradiction; ++idx) {
  state.for_each_value_while(table.vars[idx], [&](Integer val) -> bool {
    bool supported = false;
    state.for_each_value_while(table.selector, [&](Integer tuple_idx) -> bool {
      if (match(tuples[tuple_idx.raw_value][idx], val)) {
        supported = true;
        return false;
      }
      return true;
    });

    if (! supported) {
      switch (state.infer(table.vars[idx] != val, JustifyUsingRUP{})) {
      case Inference::NoChange: break;
      case Inference::Change: changed = true; break;
      case Inference::Contradiction: contradiction = true; break;
      }
    }

    return ! contradiction;
  });
}
