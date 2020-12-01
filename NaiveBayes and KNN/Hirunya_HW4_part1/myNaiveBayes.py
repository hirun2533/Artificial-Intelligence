
import numpy as np
import pandas as pd

#prepare load the data to dataframe
myTrain = pd.read_csv('./newsgroups/trainClasses.txt',sep = "\t",header = None) #get train class file
myTest  = pd.read_csv('./newsgroups/testClasses.txt',sep = "\t",header = None) #get test class file
myTrainMatrix = pd.read_csv('./newsgroups/trainMatrix.txt',sep = "\t",header = None) #get train matrix file
myTestMatrix  = pd.read_csv('./newsgroups/testMatrix.txt',sep = "\t",header = None) #get test matrix file
myTerms     =  pd.read_csv('./newsgroups/terms.txt',sep = "\t",header = None, keep_default_na = False) #get term file
myVocab = list(myTerms.values.flatten()) #take the vocabulary in term to a list and flat


#train matrix
myTrainDframe = myTrainMatrix.T #transpose array
myTrainDframe.columns = myVocab #set column data frame
myTrainDframe['Actual'] = myTrain.loc[:,1].values #add column class from label 1 in trainClass


#test matrix
myTestDframe = myTestMatrix.T   #transpose array
myTestDframe.columns = myVocab  #set column data frame
myTestDframe['Actual'] = myTest.loc[:,1].values #add column class from label 1 in testClass


#computing counts of the vocabularies and the probabilities for each of classes

AllVocab = len(myVocab) # to get V = 5500

#count vocabularies of MS class with label 0
MicrosoftProb = myTrainDframe['Actual'].value_counts(normalize = True)[0]   #use value_count and normalize equal true to compute probability for class 0

#count vocabularies of Hockey class with label 1
HockeyProb = myTrainDframe['Actual'].value_counts(normalize = True)[1]   #use value_count and normalize equal true to compute probability for class 1
TempSumMS = myTrainDframe[myTrainDframe['Actual'] == 0].sum()   #sum the number train class MS,sum across entire row
TempSumHoc = myTrainDframe[myTrainDframe['Actual'] == 1].sum()  #sum the number train class Hockey,sum across entire row
MyNumMicrosoft = TempSumMS.sum()                                #to get number of words of class MS
MyNumHockey = TempSumHoc.sum()                                  #to get number of words of class Hockey


#train component of Microsoft
#for prob vocab given in MS
MyvocabProbMS = {'vocab' : 0}
for vocab in myVocab:
    MyvocabProbMS.update(MyvocabProbMS)  #initialize to zero

MicrosoftDframe = myTrainDframe[myTrainDframe['Actual'] == 0].copy() #copy data frame for entire data

#loop to each word the probability of word given Microsoft
for vocab in myVocab:
    Tempsum = (MyNumMicrosoft + AllVocab)
    MyvocabProbMS[vocab] = (MicrosoftDframe[vocab].sum() + 1) / Tempsum  #number in data plus 1 and divide by number in MS plus total of vocabulary.


#train component of Hockey
#for prob vocab given in Hockey
MyvocabProbHockey = {'vocab' : 0}
for vocab in myVocab:
    MyvocabProbHockey.update(MyvocabProbHockey) #initialize to zero
    
HockeyDframe = myTrainDframe[myTrainDframe['Actual'] == 1].copy()          #copy data frame for entire data

#loop to each word the probability of word given in Hockey
for vocab in myVocab:
    Tempsum = (MyNumHockey + AllVocab)
    MyvocabProbHockey[vocab] = (HockeyDframe[vocab].sum() + 1) / Tempsum  #number in data plus 1 and divide by number in hockey plus total of vocabulary.


#test/classification Naive Bayes working for classifier the class probability.

MytempRow = myTestDframe.loc[1]      #set to each time a row

def MyNaiveBayesClassification(MytempRow):

    MymicrosoftProb = np.log(MicrosoftProb)                                 #take log to avoid underflow
    MyhockeyProb = np.log(HockeyProb)                                       #take log to avoid underflow
    #loop in each vocabulary
    for vocab in myVocab:
        if MytempRow[vocab] > 0:                                            #if the word appear more than zero time 
            #take log to avoid underflow
            MyTempMS = MytempRow[vocab] + np.log(MyvocabProbMS[vocab])      #calculate for MS
            MyTempHock = MytempRow[vocab]+ np.log(MyvocabProbHockey[vocab]) #calculate for hockey
            MymicrosoftProb += MyTempMS                                     #sum everything togehter
            MyhockeyProb += MyTempHock                                      #sum everything together
    if MymicrosoftProb > MyhockeyProb: return 0                             #and then compare the value between MS and Hockey 
    else: return 1


# for writeup Q1.
myTestDframe['Predicted'] = myTestDframe.apply(MyNaiveBayesClassification, axis = 1) #apply MyNaiveBayesClassification to data frame
TempComp = myTestDframe['Actual'] == myTestDframe['Predicted']                       #compare them and store to TempCompare.
MyOverall = (TempComp).sum() / len(myTestDframe) * 100                               #sum and divide by the lenght
print('The overall classification accuracy = ', MyOverall)                           #print result


#for writeup Q2.
print(myTestDframe[['Actual','Predicted']].head(20))                                #print table result


# for writeup Q3.
MyTerms = ["program", "includ", "match", "game", "plai", "window", "file", "subject", "write"]  #take the particular vocabularies
for myTerm in MyTerms:                                                                          #loop throught each vacab
    MyMSProbTerms = MyvocabProbMS[myTerm]                                                       #take the train model of Microsoft
    MyHockProbTerms = MyvocabProbHockey[myTerm]                                                 #take the test model of Microsoft
    print('word in terms: %s,  P(Microsoft): %.6f,  P(Hockey): %.6f' %(myTerm, MyMSProbTerms, MyHockProbTerms)) #print the result

