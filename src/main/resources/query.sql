select
  Hero.name as Hero,
  Villain.name as Villain
from Hero
join Villain on Hero.id = Villain.archnemesis
where Hero.id < 2
order by Hero.id
