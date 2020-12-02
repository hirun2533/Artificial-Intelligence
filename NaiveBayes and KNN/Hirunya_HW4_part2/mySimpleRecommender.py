
import pandas as pd
import numpy as np



dframe = pd.read_excel('knn-csc480-a4.xls', na_values=' ').set_index('Unnamed: 0')

myTrain = dframe.loc['U1':'U20',:]
myTest = dframe.loc['NU1':'NU5',:]

def myPredictRating(myTrain, myTestRow, myCol, k):
    myTrainValid = myTrain.dropna(subset=[myCol])
    myTrainMat = myTrainValid.drop(columns = myCol).fillna(0).values
    myTestMat = myTestRow.drop(columns = myCol).fillna(0).values

    myCorrs = np.zeros(myTrainMat.shape[0])
    for i in range(myTrainMat.shape[0]):
        myCorrs[i] = correlation(myTrainMat[i], myTestMat)

    myTopk = np.argsort(-myCorrs)[:k]
    ratings = myTrainValid[myCol].iloc[myTopk].values
    sims = myCorrs[myTopk]
 
    prediction = np.sum(sims*ratings)/np.sum(sims)
    
    return prediction



def correlation(X, Y): 
    mysumX = 0
    mysumY = 0
    mysumXY = 0
    mysqrSumX = 0
    mysqrSumY = 0
    tempN = len(X)
    for i in range(tempN):
         
        mysumX = mysumX + X[i]
        mysumY = mysumY + Y[i]
        mysumXY = mysumXY + X[i] * Y[i]
        mysqrSumX = mysqrSumX + X[i] * X[i]
        mysqrSumY = mysqrSumY + Y[i] * Y[i]
                 
   
    mycorr = (tempN * mysumXY - mysumX * mysumY)/(np.sqrt((tempN * mysqrSumX - mysumX * mysumX)* 
            (tempN * mysqrSumY - mysumY * mysumY))) 
    return mycorr




def myMaeCalculation(myTrain, myTest, k):
    errors = []
    for user in ['NU1', 'NU2', 'NU3', 'NU4', 'NU5']:
        myTestRow = myTest.loc[user,:]
        myRatedColumns = myTestRow.dropna().index
        for myCol in myRatedColumns:
            myPrediction = myPredictRating(myTrain, myTestRow, myCol, k)
            errors.append(myPrediction - myTestRow[myCol])
    return np.mean(np.abs(np.array(errors)))



myErr = myMaeCalculation(myTrain, myTest, 3)
print('Mean Absolute Error with k = 3 on the Test Set:', myErr)




print('{0:5s} {1:4s}'.format('K', 'MAE'))
errors = []
for k in range(1, 21):
    myErr = myMaeCalculation(myTrain, myTest, k)
    errors.append(myErr)
    print('{0:<5d} {1:.4f}'.format(k, myErr))
    



print('{0:5s} {1:20s} {2:4s}'.format('User', 'Item', 'Rating'))
for user in ['NU1', 'NU2']:
    myTestRow = myTest.loc[user,:]
    myItems = myTestRow.index[myTestRow.isna()]
    for myCol in myItems:
        myPrediction = myPredictRating(myTrain, myTestRow, myCol, 8)
        print('{0:<5s} {1:<20s} {2:4.3f}'.format(user, myCol, myPrediction))




def myTop3Recommendation(myTrain, user, k, top3):
    myTestRow = myTrain.loc[user,:]
    myTrainExcluded = myTrain.drop(index=user)
    myItems = myTestRow.index[myTestRow.isna()]
    myRating = []
    for myCol in myItems:
        myRating.append(myPredictRating(myTrainExcluded, myTestRow, myCol, k))
    
    myRes = pd.DataFrame({'user': user, 'item': myItems, 'Rating': myRating}).sort_values('Rating', ascending=False) #
    return myRes.head(top3)



generate = []
for user in ['U2', 'U5', 'U13', 'U20']:
    generate.append(myTop3Recommendation(myTrain, user, 4, 3))
generate = pd.concat(generate).reset_index(drop=True)
print(generate)                                                 

