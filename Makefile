all : tables graphs
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' all-different
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' linear
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' table
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' parallel
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' proofs

TABLES =

GRAPHS = \

graphs : $(GRAPHS)

tables : $(TABLES)

gen-graph-%.pdf : graph-%.gnuplot common.gnuplot glasgow.pal
	gnuplot $<
	sed -i -e '19,20s/^\(\\path.*\)/\% \1/' gen-graph-$*.tex # epic haxx
	latexmk -pdf gen-graph-$*

