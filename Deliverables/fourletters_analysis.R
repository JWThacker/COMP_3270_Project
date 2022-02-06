library(ggplot2)
library(dplyr)
library(readr)
library(tidyr)
library(stringr)

path <- "/Users/jaredthacker/COMP_3270/class_project/autocomplete-start/src"
setwd(path)

df.four <- distinct(read.csv2('bench_fourletters.txt', sep='\t'))

df.four.half <- distinct(read.csv2('bench_fourlettershalf.txt', sep='\t'))

df.four.half <- gather(df.four.half, "Implementation", "Time", Brute, Binary, Trie)
df.four.half$Time <-  as.numeric(df.four.half$Time)
df.four.half$file_name <- 'fourletterwordshalf.txt'
half.prefix.split <- str_split_fixed(unlist(df.four.half$Prefix), pattern='_', n=2)
df.four.half$word <- half.prefix.split[,1]
df.four.half$k_matches <- half.prefix.split[,2]
df.four.half.topMatches <- df.four.half %>% filter(k_matches != "")
df.four.half.topMatch <- df.four.half %>% filter(k_matches == "")
df.four.half.topMatch <- df.four.half.topMatch %>% select(-k_matches)
df.four.half.topMatch <- df.four.half.topMatch %>% mutate(prefix_length = str_length(word))
df.four.half.topMatches <- df.four.half.topMatches %>% mutate(prefix_length = str_length(word))
df.four.half.topMatches <- tibble(df.four.half.topMatches)
df.four.half.topMatches.summ <- df.four.half.topMatches %>%
  group_by(prefix_length, Implementation, file_name) %>%
  summarise(mean_time=mean(Time))

df.four.half.topMatch.summ <- df.four.half.topMatch %>%
  group_by(prefix_length, Implementation, file_name) %>%
  summarise(mean_time=mean(Time))

df.four <- gather(df.four, "Implementation", "Time", Brute, Binary, Trie)
df.four$Time <- as.numeric(df.four$Time)
df.four$file_name <- 'fourletterwords.txt'
full.prefix.split <- str_split_fixed(unlist(df.four$Prefix), pattern='_', n=2)
df.four$word <- full.prefix.split[,1]
df.four$k_matches <- full.prefix.split[,2]
df.four.topMatches <- df.four %>% filter(k_matches != "")
df.four.topMatch <- df.four %>% filter(k_matches == "")
df.four.topMatch <- df.four.topMatch %>% mutate(prefix_length = str_length(word))
df.four.topMatches <- df.four.topMatches %>% mutate(prefix_length = str_length(word))
df.four.topMatches.summ <- df.four.topMatches %>%
  group_by(prefix_length, Implementation, file_name) %>%
  summarise(mean_time=mean(Time))
  
df.four.topMatch.summ <- df.four.topMatch %>%
  group_by(prefix_length, Implementation, file_name) %>%
  summarise(mean_time=mean(Time))

df.four.combined <- bind_rows(df.four.topMatches.summ, df.four.half.topMatches.summ)

ggplot(df.four.combined, aes(x=Implementation, y=mean_time, color=file_name, size=prefix_length)) +
  geom_point()

df.four.combined.trie <- df.four.combined %>% filter(Implementation == "Trie")
ggplot(df.four.combined.trie, aes(x=prefix_length, y=mean_time, fill=file_name)) +
  geom_bar(stat='identity', position=position_dodge()) +
  labs(x='Prefix Length', y='Mean Runtime', title='Mean Runtime vs. Prefix Length (Trie.topMatches)',
       fill='File Name') +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))

df.four.combined.topMatch <- bind_rows(df.four.topMatch.summ, df.four.half.topMatch.summ)
df.four.combined.topMatch.Trie <- df.four.combined.topMatch %>% filter(Implementation == 'Trie')
ggplot(df.four.combined.topMatch.Trie, aes(x=prefix_length, y=mean_time, fill=file_name)) +
  geom_bar(stat='identity', position=position_dodge()) +
  labs(x='Prefix Length', y='Mean Time', title='Mean Runtime vs. Prefix Length (Trie.topMatch)',
       fill='File Name') +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))

df.four.half.topMatch
df.four.half.topMatches

df.four.topMatch
df.four.topMatches

df.four.topMatches.combined <- bind_rows(df.four.topMatches, df.four.half.topMatches)
df.four.topMatch.combined <- bind_rows(df.four.topMatch, df.four.half.topMatch)

df.four.topMatches.combined.summ <- df.four.topMatches.combined %>%
  group_by(file_name, Implementation) %>%
  summarise(mean_time=mean(Time))
df.four.topMatch.combined.summ <- df.four.topMatch.combined %>%
  group_by(file_name, Implementation) %>%
  summarise(mean_time=mean(Time))

ggplot(df.four.topMatches.combined.summ, aes(x=Implementation, y=mean_time,
                                             fill=file_name)) +
  labs(x=NULL, y='Mean Time', title='Mean Runtime vs Implementation (topMatches)',
       fill='File Name') +
  geom_bar(stat='identity', position=position_dodge()) +
  geom_text(aes(label=round(mean_time, 5)),
            vjust=-0.5, position=position_dodge(0.9)) +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))

ggplot(df.four.topMatch.combined.summ, aes(x=Implementation, y=mean_time,
                                             fill=file_name)) +
  labs(x=NULL, y='Mean Time', title='Mean Runtime vs Implementation (topMatch)',
       fill='File Name') +
  geom_bar(stat='identity', position=position_dodge()) +
  geom_text(aes(label=round(mean_time, 6)),
            vjust=-0.5, position=position_dodge(0.9)) +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))



