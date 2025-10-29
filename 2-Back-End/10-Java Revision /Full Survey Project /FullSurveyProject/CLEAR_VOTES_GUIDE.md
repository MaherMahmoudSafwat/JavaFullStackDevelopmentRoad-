# 🗑️ Clear All Votes - Quick Guide

## Issues Fixed:
1. ✅ **UserId localStorage issue** - Frontend now checks both 'userId' and 'UserId'
2. ✅ **Clear votes endpoint** - Added temporary endpoint to reset all votes

---

## How to Clear All Votes:

### Step 1: Start Backend
```bash
cd "/home/maher/Desktop/JavaFullStackDevelopmentRoad-/2-Back-End/Java Revision /Full Survey Project /FullSurveyProject"
./mvnw spring-boot:run
```

### Step 2: Call the Clear Endpoint
Open a new terminal and run:
```bash
curl -X DELETE http://localhost:8080/Surveys/ClearAllVotes
```

**OR** use your browser and go to:
```
http://localhost:8080/Surveys/ClearAllVotes
```

You should see: `"All votes cleared and answer counts reset to 0"`

### Step 3: Verify in Database
All votes are deleted from the `Votes` table, and all answer counts are reset to 0.

---

## After Clearing Votes - DELETE THE ENDPOINT

Once you've cleared the votes, **remove this endpoint** from the code:

### File: `SurveyController.java`
**DELETE these lines (89-95):**
```java
// TEMPORARY ENDPOINT - Delete this after clearing votes
@DeleteMapping("/ClearAllVotes")
public String ClearAllVotes()
{
    SurveysService.ClearAllVotesAndResetCounts();
    return "All votes cleared and answer counts reset to 0";
}
```

### File: `SurveyService.java`
**DELETE these lines (152-157):**
```java
@Transactional
public void ClearAllVotesAndResetCounts()
{
    VoteService.ClearAllVotes();
    AnswerService.ResetAllVoteCounts();
}
```

---

## What Was Changed:

### Frontend:
- **show-all-surveys.ts**: Now checks both `localStorage.getItem('userId')` and `localStorage.getItem('UserId')`

### Backend (TEMPORARY - Delete after use):
- **VoteRepository.java**: Added `deleteAllVotes()` method
- **VoteService.java**: Added `ClearAllVotes()` method
- **AnswersRepository.java**: Added `resetAllVoteCounts()` method
- **AnswersService.java**: Added `ResetAllVoteCounts()` method
- **SurveyService.java**: Added `ClearAllVotesAndResetCounts()` method
- **SurveyController.java**: Added `/ClearAllVotes` endpoint

---

## Testing After Clearing:
1. Restart backend
2. Start frontend: `ng serve`
3. Login with your account
4. Go to "Show All Surveys"
5. Try voting - should work now!
6. Check percentages show 1 decimal place
7. Try changing vote - should work
8. Try clicking same answer - should do nothing

---

## 🚨 IMPORTANT:
**Delete the temporary endpoint after clearing votes!** It's a security risk to leave it in production.