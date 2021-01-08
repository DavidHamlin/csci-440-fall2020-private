
                SELECT
                    artists.Name as ArtistName, SUM(tracks.UnitPrice) AS totPrice
                FROM tracks

                       JOIN albums ON
                           albums.AlbumId = tracks.AlbumId
                       JOIN artists ON
                           albums.ArtistId = artists.ArtistId


                       GROUP BY ArtistName
                HAVING totPrice > 10;
                create index idx_ArtistName3 on artists(Name);


