*************************************************************************************************************************                                 
                                     Group 8 - Movie Recommendation System
*************************************************************************************************************************
                                                    Anshul Jain
                                              ArunKumar Subhramanyan
                                                    Rahul Jain
                                                  Ronak Dhalawat
                                              Vivekanand Nolastname
-------------------------------------------------------------------------------------------------------------------------
Dataset: The orignal dataset is from Movie Tweeting Dataset: https://github.com/sidooms/MovieTweetings
_________________________________________________________________________________________________________________________
                                                       Folders
-------------------------------------------------------------------------------------------------------------------------
Dataset: We have converted the orignal dataset into a format that is used in our implementation. 
The Dataset folder contains the dataset we have used for our implementation

Results: This folder contains various results of the experiments we have performed.
The final result file is large to be shared and so it is uploaded at: 
http://www.public.asu.edu/~ajain89/MovieRecommendation/RecommendationResults.xlsx
-------------------------------------------------------------------------------------------------------------------------
                                                      Java Files
_________________________________________________________________________________________________________________________

1. CollaborativeItem.java: This file is used to create the user vs movie rating matrix. 
   This is the most important part of collaborative filtering implementation. 
   It takes MovieIDs.txt and DataSet.txt as input files and creates CollaborativeDataset.csv file as the output result. 
   Cosine similarity will be calculated using this matrix.
2. CosineSimilarityCalculation.java: This file is used for calculating the cosine similarity between users. 
   It takes MovieIDs.txt and DataSet.txt as input files and creates CosineSymmetryMatrix.csv file as the output result.
3. CollaborativeFiltering.java: This file is used for creating the data that is used for generating the results. 
   It takes MovieIDs.txt and DataSet.txt as input files and creates TestOutput.csv file as the output result. 
   The output file contains {USER 1,USER 2,MOVIE,RATING 1,RATING 2,DIFFERENCE}
4. Recommendor.java: Finally using the above file, Recommendor.java will be recommending movies based on user similarity. 
   This file can be used as a recommender system.

*************************************************************************************************************************    