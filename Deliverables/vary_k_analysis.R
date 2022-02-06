path <- "/Users/jaredthacker/COMP_3270/class_project/autocomplete-start/src"
setwd(path)

df.words.333333 <- distinct(read.csv2('words-333333-var-k.txt', sep='\t'))
df.words.333333 <- gather(df.words.333333, "Implementation", "Time", Brute, Binary, Trie)
df.words.333333$Time <- as.numeric(df.words.333333$Time)
prefix_split <- str_split_fixed(unlist(df.words.333333$Prefix), pattern='_',
                                n=2)
df.words.333333 <- df.words.333333 %>% mutate(word=prefix_split[, 1], k=prefix_split[, 2])
df.words.333333.topMatch <- df.words.333333 %>% filter(k == "")
df.words.333333.topMatches <- df.words.333333 %>% filter(k != "")
df.words.333333.topMatches <- df.words.333333.topMatches %>% filter(Implementation != "Binary")
df.words.333333.topMatches.summ <- df.words.333333.topMatches %>%
  group_by(k, Implementation) %>%
  summarise(mean_time=mean(Time))

ggplot(df.words.333333.topMatches.summ, aes(k, mean_time, fill=Implementation)) +
  geom_bar(stat='identity', position=position_dodge()) +
  labs(x='K', y='Mean Runtime', title='Mean Runtime vs. K (topMatches)',
       fill='Implementation') +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))

df.words.333333.topMatches.summ.Trie <- df.words.333333.topMatches.summ %>% filter(Implementation == "Trie")

ggplot(df.words.333333.topMatches.summ.Trie, aes(k, mean_time)) +
  geom_bar(stat='identity', position=position_dodge(), fill='blue',
           color='black') +
  labs(x='K', y='Mean Runtime', title='Mean Runtime vs. K (Only For Trie.topMatches)') +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))

df.words.333333.topMatch <- df.words.333333 %>% filter(k == "")
df.words.333333.topMatches <- df.words.333333 %>% filter(k != "")

df.words.333333.topMatch.summ <- df.words.333333.topMatch %>%
  group_by(Implementation) %>%
  summarise(mean_time=mean(Time))

df.words.333333.topMatches.summ <- df.words.333333.topMatches %>%
  group_by(Implementation) %>%
  summarise(mean_time=mean(Time))

ggplot(df.words.333333.topMatch.summ, aes(x=Implementation, y=mean_time)) +
  geom_bar(stat='identity', fill='blue') +
  geom_text(aes(label=mean_time), vjust=-.5) +
  labs(y='Mean Time', title='Mean Runtime vs. Algorithm Implementation (topMatch)') +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))

ggplot(df.words.333333.topMatches.summ, aes(x=Implementation, y=mean_time)) +
  geom_bar(stat='identity', fill='blue') +
  geom_text(aes(label=mean_time), vjust=-.5) +
  labs(y='Mean Time', title='Mean Runtime vs. Algorithm Implementation (topMatches)') +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))

ggplot(df.words.333333.topMatch, aes(y=Time, x=Prefix, fill=Implementation)) +
  geom_bar(stat='identity', position=position_dodge()) +
  labs(title='Time vs Prefix (topMatch)') +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))

ggplot(df.words.333333.topMatches, aes(x=Time, y=Prefix, fill=Implementation)) +
  geom_bar(stat='identity', position=position_dodge()) +
  labs(title='Time vs Prefix (topMatches)') +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))

df.words.333333.topMatch <- df.words.333333.topMatch %>%
  filter(Implementation != 'Brute')

df.words.333333.topMatches <- df.words.333333.topMatches %>%
  filter(Implementation != 'Brute')

ggplot(df.words.333333.topMatch, aes(y=Time, x=Prefix, fill=Implementation)) +
  geom_bar(stat='identity', position=position_dodge()) +
  labs(title='Time vs Prefix (topMatch)') +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))

ggplot(df.words.333333.topMatches, aes(x=Time, y=Prefix, fill=Implementation)) +
  geom_bar(stat='identity', position=position_dodge()) +
  labs(title='Time vs Prefix (topMatches)') +
  theme_bw() +
  theme(plot.title=element_text(hjust=0.5))




