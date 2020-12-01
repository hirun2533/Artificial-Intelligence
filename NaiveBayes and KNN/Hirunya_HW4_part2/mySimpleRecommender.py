
import pandas as pd
import numpy as np


#for prepare data to dataframe from the data file.
dframe = pd.read_excel('knn-csc480-a4.xls', na_values=' ').set_index('Unnamed: 0')
#training data from user-item rating
myTrain = dframe.loc['U1':'U20',:]
myTest = dframe.loc['NU1':'NU5',:]

#it will generate a prediction for a test user
#It will measure the similarity of that new user test to every instance user test in the training data 
#in order to identify the K most similar users to the value.
#the ratings of the K neighbors are measured by the correlation fuction to the target user,
#and then sum of all these ratings and divided by the sum of all the similarities across the K neighbors.

def myPredictRating(myTrain, myTestRow, myCol, k):
    myTrainValid = myTrain.dropna(subset=[myCol])
    myTrainMat = myTrainValid.drop(columns = myCol).fillna(0).values #fillter 0 out
    myTestMat = myTestRow.drop(columns = myCol).fillna(0).values     #fillter 0 out

    myCorrs = np.zeros(myTrainMat.shape[0])
    for i in range(myTrainMat.shape[0]):                        #loop through the length
        myCorrs[i] = correlation(myTrainMat[i], myTestMat)      #invoke the correlation function

    myTopk = np.argsort(-myCorrs)[:k]                           #sort array
    ratings = myTrainValid[myCol].iloc[myTopk].values           #get the value position
    sims = myCorrs[myTopk]                                      #get the array put in sims variable
 
    prediction = np.sum(sims*ratings)/np.sum(sims)              #calculate sum up all thses values
    
    return prediction                                           #return the value


#pearson corelation function which will find the strong relation between data

def correlation(X, Y): 
    mysumX = 0      #initial to 0
    mysumY = 0      #initial to 0
    mysumXY = 0     #initial to 0
    mysqrSumX = 0   #initial to 0
    mysqrSumY = 0   #initial to 0
    tempN = len(X)           #declare length
    for i in range(tempN):   #for loop iterate through range in temp
         
        mysumX = mysumX + X[i]  # sum up the elements in arr of x
        mysumY = mysumY + Y[i]  # sum up the elements in arr of y 
        mysumXY = mysumXY + X[i] * Y[i] # sum up arr x index i and multipy with arr of y index i
        mysqrSumX = mysqrSumX + X[i] * X[i]  # sum up the square of elements of arr
        mysqrSumY = mysqrSumY + Y[i] * Y[i]  # sum up the square of elements of arr
                 
    # and then utilize all the formulas for calculate the correlation  
    mycorr = (tempN * mysumXY - mysumX * mysumY)/(np.sqrt((tempN * mysqrSumX - mysumX * mysumX)* 
            (tempN * mysqrSumY - mysumY * mysumY))) 
    return mycorr        #retun the result


#Difference between predicted scores and the actual results (from myPredictRating set)

def myMaeCalculation(myTrain, myTest, k):
    errors = []                                                             #declare the array
    for user in ['NU1', 'NU2', 'NU3', 'NU4', 'NU5']:                        #loop iterate through particular user
        myTestRow = myTest.loc[user,:]                                      #get the particular lebel
        myRatedColumns = myTestRow.dropna().index                           #drop it
        for myCol in myRatedColumns:                                        #loop iterate through column
            myPrediction = myPredictRating(myTrain, myTestRow, myCol, k)    #invoke the function predict
            errors.append(myPrediction - myTestRow[myCol])                  #calculate which is predict - actual
    return np.mean(np.abs(np.array(errors)))                                #retunr the value


#for invoke function MAE calculation to show resulf MAE with K equal 3
myErr = myMaeCalculation(myTrain, myTest, 3)                     #invoke the function
print('Mean Absolute Error with k = 3 on the Test Set:', myErr)  #and then print the result


#to find the several K values which are K = 1 to K = 20 

print('{0:5s} {1:4s}'.format('K', 'MAE'))           #print header
errors = []                                         #declare array
for k in range(1, 21):                              #loop iterate through particular range
    myErr = myMaeCalculation(myTrain, myTest, k)    #invoke the function
    errors.append(myErr)                            #append it
    print('{0:<5d} {1:.4f}'.format(k, myErr))       #and then print the result
    


#According the K value that I got which is K =8 to predict rating for NU1 and NU2 
#for the all of the items that have not been rate for these users. 

print('{0:5s} {1:20s} {2:4s}'.format('User', 'Item', 'Rating'))  #print header
for user in ['NU1', 'NU2']:                                      #loop iterate through particular user
    myTestRow = myTest.loc[user,:]                               #get the particular lebel
    myItems = myTestRow.index[myTestRow.isna()]                  #get all items that is (NA).
    for myCol in myItems:                                        #loop threough the items
        myPrediction = myPredictRating(myTrain, myTestRow, myCol, 8)         #invoke the function
        print('{0:<5s} {1:<20s} {2:4.3f}'.format(user, myCol, myPrediction)) #and print the result


#this function will generate the top 3 recommendation of the particular user
#in the for loop through the items to generate the predicted rating 

def myTop3Recommendation(myTrain, user, k, top3):
    myTestRow = myTrain.loc[user,:]              #get the particular lebel
    myTrainExcluded = myTrain.drop(index=user)   #and then drop that index = user 
    myItems = myTestRow.index[myTestRow.isna()]  #get all items that is (NA). 
    myRating = []                                #declare array
    for myCol in myItems:                        #loop threough the items
        myRating.append(myPredictRating(myTrainExcluded, myTestRow, myCol, k)) #generate predicted rate
    
    myRes = pd.DataFrame({'user': user, 'item': myItems, 'Rating': myRating}).sort_values('Rating', ascending=False) #use panda to present the result and sort it
    return myRes.head(top3) #and return the result


#for invoke the top 3 reccommender function for User 2,5,13 and 20

generate = []                               #declare array
for user in ['U2', 'U5', 'U13', 'U20']:     #loop iterate through particular user
    generate.append(myTop3Recommendation(myTrain, user, 4, 3))  #append it
generate = pd.concat(generate).reset_index(drop=True)           #show the result in order   
print(generate)                                                 #print the result

