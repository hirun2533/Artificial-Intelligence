
import numpy as np
import pandas as pd


myTrain = pd.read_csv('./newsgroups/trainClasses.txt',sep = "\t",header = None)
myTest  = pd.read_csv('./newsgroups/testClasses.txt',sep = "\t",header = None)
myTrainMatrix = pd.read_csv('./newsgroups/trainMatrix.txt',sep = "\t",header = None)
myTestMatrix  = pd.read_csv('./newsgroups/testMatrix.txt',sep = "\t",header = None)
myTerms     =  pd.read_csv('./newsgroups/terms.txt',sep = "\t",header = None, keep_default_na = False)
myVocab = list(myTerms.values.flatten()) #


myTrainDframe = myTrainMatrix.T
myTrainDframe.columns = myVocab
myTrainDframe['Actual'] = myTrain.loc[:,1].values


myTestDframe = myTestMatrix.T
myTestDframe.columns = myVocab  e
myTestDframe['Actual'] = myTest.loc[:,1].values

AllVocab = len(myVocab)


MicrosoftProb = myTrainDframe['Actual'].value_counts(normalize = True)[0]

HockeyProb = myTrainDframe['Actual'].value_counts(normalize = True)[1]
TempSumMS = myTrainDframe[myTrainDframe['Actual'] == 0].sum()
TempSumHoc = myTrainDframe[myTrainDframe['Actual'] == 1].sum()
MyNumMicrosoft = TempSumMS.sum()
MyNumHockey = TempSumHoc.sum()                                  #



MyvocabProbMS = {'vocab' : 0}
for vocab in myVocab:
    MyvocabProbMS.update(MyvocabProbMS)

MicrosoftDframe = myTrainDframe[myTrainDframe['Actual'] == 0].copy()

for vocab in myVocab:
    Tempsum = (MyNumMicrosoft + AllVocab)
    MyvocabProbMS[vocab] = (MicrosoftDframe[vocab].sum() + 1) / Tempsum



MyvocabProbHockey = {'vocab' : 0}
for vocab in myVocab:
    MyvocabProbHockey.update(MyvocabProbHockey)
    
HockeyDframe = myTrainDframe[myTrainDframe['Actual'] == 1].copy()


for vocab in myVocab:
    Tempsum = (MyNumHockey + AllVocab)
    MyvocabProbHockey[vocab] = (HockeyDframe[vocab].sum() + 1) / Tempsum



MytempRow = myTestDframe.loc[1]
def MyNaiveBayesClassification(MytempRow):

    MymicrosoftProb = np.log(MicrosoftProb)
    MyhockeyProb = np.log(HockeyProb)
    for vocab in myVocab:
        if MytempRow[vocab] > 0:
            MyTempMS = MytempRow[vocab] + np.log(MyvocabProbMS[vocab])
            MyTempHock = MytempRow[vocab]+ np.log(MyvocabProbHockey[vocab])
            MymicrosoftProb += MyTempMS
            MyhockeyProb += MyTempHock
    if MymicrosoftProb > MyhockeyProb: return 0
    else: return 1


# for writeup Q1.
myTestDframe['Predicted'] = myTestDframe.apply(MyNaiveBayesClassification, axis = 1)
TempComp = myTestDframe['Actual'] == myTestDframe['Predicted']
MyOverall = (TempComp).sum() / len(myTestDframe) * 100
print('The overall classification accuracy = ', MyOverall)

#for writeup Q2.
print(myTestDframe[['Actual','Predicted']].head(20))

# for writeup Q3.
MyTerms = ["program", "includ", "match", "game", "plai", "window", "file", "subject", "write"]
for myTerm in MyTerms:
    MyMSProbTerms = MyvocabProbMS[myTerm]
    MyHockProbTerms = MyvocabProbHockey[myTerm]
    print('word in terms: %s,  P(Microsoft): %.6f,  P(Hockey): %.6f' %(myTerm, MyMSProbTerms, MyHockProbTerms)) 

