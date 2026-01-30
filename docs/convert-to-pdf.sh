#!/bin/sh
asciidoctor-pdf zacepco-mechanics-ru.adoc -o ./pdf/zacepco-mechanics-ru.pdf
asciidoctor-pdf script-template-ru.adoc -o ./pdf/script-template-ru.pdf
asciidoctor-pdf script-guide-ru.adoc -o ./pdf/script-guide-ru.pdf

# to install converter:$ 
# sudo su -
# gem install asciidoctor-pdf --pre