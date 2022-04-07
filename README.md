# News Tracker

rss feed -> urls -> database

article

- id
- title
- content
- author
- date published
- date updated
- url
- source (fk)
- date retrieved
- date last analysed
- sentiment
- tags
- linked articles

source

- id
- title
- root domain/website

tag/keyword

- name

link

- between articles
- strength of link (0 - 100)
    - number of shared tags
    - 'proximity' of subjects
    - explicit link if from same source