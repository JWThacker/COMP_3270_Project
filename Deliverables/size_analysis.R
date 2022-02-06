library(readxl)
library(tidyr)
library(dplyr)

path <- "/Users/jaredthacker/COMP_3270/class_project/Deliverables"
setwd(path)
xl.file.name <- 'constructor_speeds-words.xlsx'
sheets <- lst('words-333333', 'tiny', 'small', 'fortune1000', 'baby-names',
              'fourletterwords', 'words-333333half')
df <- data.frame()
for (sheet in sheets) {
  xl.file <- read_excel(xl.file.name, sheet=sheet)
  xl.file$file <- sheet
  df <- bind_rows(df, xl.file)
}

df <- gather(df, 'Implementation', 'Time', Brute, Binary, Trie)

xticks <- pretty(0:500000, n=5)
xtick.labels <- format(xticks, big.mark=',', scientific=F)
ggplot(df, aes(x=Size, y=Time, color=Implementation)) +
  scale_x_continuous(labels=xtick.labels) +
  geom_line(size=4) +
  geom_point(color='black', size=4) +
  labs(title='Creation Runtime vs. Source File Size') +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))
