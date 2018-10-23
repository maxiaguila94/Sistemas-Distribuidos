


# specify your main target here:
NAME=Laboratorio-3

all: convert $(NAME).pdf clean

convert:
	jupyter-nbconvert $(NAME).ipynb --to latex --template template.tplx


# specify the main file and all the files that you are including
SOURCE=$(NAME).tex references.bib 

%.pdf: %.tex $(SOURCE)
	pdflatex $*
	bibtex $*
	pdflatex $*
	pdflatex $*
	pdflatex $*

clean:
	rm -f *.bak *~ *.log *.blg *.bbl *.aux *.toc *.cut *.out *.tmp *.tpm *.adx *.adx.hyp *.idx *.ilg *.ind \
	*.and *.glg *.glo *.gls *.wdx *.wnd *.wrd *.wdv *.ldx *.lnd *.rdx *.rnd *.xdv *.fdb *.fdb_latexmk *.fls \
	*.synctex.gz *.run.xml *.bcf

realclean: clean
	rm -f *.dvi *.ps *.pdf *.bcf
