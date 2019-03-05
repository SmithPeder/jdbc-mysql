USE MD;

select MovieId, Title, ProductionYear, DirectorID from Movie;

select Name, Birthyear from Actor where Birthyear > 1960;

select Name, Birthyear from Actor where 1970 <= Birthyear and Birthyear < 1980 order by Name; 

select Title, Role from Actor as A join ActorInMovie as AIM on A.ActorID = AIM.ActorID join Movie as M on M.MovieID = AIM.MovieID where A.Name = "Morgan Freeman";
