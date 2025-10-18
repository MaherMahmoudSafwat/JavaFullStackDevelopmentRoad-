# 🧪 Testing Guide - Voting Feature

## ✅ Backend Status: RUNNING ✅

The backend is already running on `http://localhost:8080`

---

## 🚀 Quick Start

### **Step 1: Start Frontend**

Open a new terminal and run:

```bash
cd "/home/maher/Desktop/JavaFullStackDevelopmentRoad-/2-Back-End/Java Revision /Full Survey Project /FullSurveyProjectFront-endPart"
ng serve
```

Wait for: `✔ Compiled successfully.`

### **Step 2: Open Browser**

Navigate to: **http://localhost:4200**

### **Step 3: Login**

Use your credentials to login

### **Step 4: Go to "Show All Surveys"**

Click on the "Show All Surveys" button in the dashboard

---

## 🧪 Test Scenarios

### **Test 1: View Surveys**

**Expected:**
- ✅ See all surveys from different users
- ✅ Each survey shows user profile image (or initials)
- ✅ Each survey shows username
- ✅ Each survey shows question
- ✅ Each answer shows:
  - Letter (A, B, C, D)
  - Answer text
  - Percentage
  - Vote count
  - **Dark gray horizontal progress bar**

---

### **Test 2: Vote on an Answer**

**Steps:**
1. Hover over any answer
2. Notice cursor becomes pointer
3. Notice border turns purple
4. Click the answer

**Expected:**
- ✅ Vote sent to backend (check console logs)
- ✅ "✓ Voted" badge appears next to answer
- ✅ Answer gets **purple border** (3px thick)
- ✅ Progress bar turns **purple gradient**
- ✅ Vote count increases by 1
- ✅ Percentages recalculate for all answers
- ✅ Progress bars grow/shrink smoothly

**Console Logs:**
```
🗳️ Voting on survey 152, answer 456
✅ Vote recorded successfully: [...]
📊 Updated percentages: ["Answer A: 30%", "Answer B: 45%", ...]
```

---

### **Test 3: Change Vote**

**Steps:**
1. Click on a different answer in the same survey

**Expected:**
- ✅ Previous answer loses "✓ Voted" badge
- ✅ Previous answer border becomes gray
- ✅ Previous answer progress bar becomes dark gray
- ✅ New answer gets "✓ Voted" badge
- ✅ New answer gets purple border
- ✅ New answer progress bar becomes purple
- ✅ Vote counts update
- ✅ Percentages recalculate

---

### **Test 4: Vote on Multiple Surveys**

**Steps:**
1. Vote on first survey
2. Scroll down
3. Vote on second survey
4. Vote on third survey

**Expected:**
- ✅ Each survey tracks your vote independently
- ✅ Each voted answer shows "✓ Voted" badge
- ✅ Each voted answer has purple styling
- ✅ All percentages update correctly

---

### **Test 5: Refresh Page**

**Steps:**
1. Vote on a survey
2. Refresh the page (F5)

**Expected:**
- ✅ Vote counts persist (saved in database)
- ✅ Percentages persist
- ✅ "✓ Voted" badge disappears (frontend tracking resets)
- ✅ Can vote again

**Note:** The frontend tracks votes in memory. After refresh, you can vote again because the frontend doesn't know you already voted. This is expected behavior for "multiple votes allowed" mode.

---

### **Test 6: Progress Bar Animation**

**Steps:**
1. Find a survey with low percentages
2. Vote on an answer
3. Watch the progress bar

**Expected:**
- ✅ Progress bar grows smoothly (0.5s animation)
- ✅ Other progress bars shrink smoothly
- ✅ Color changes from dark gray to purple gradient
- ✅ Smooth transition

---

### **Test 7: Mobile Responsive**

**Steps:**
1. Open browser DevTools (F12)
2. Toggle device toolbar (Ctrl+Shift+M)
3. Select mobile device (iPhone, Android)

**Expected:**
- ✅ Layout adapts to mobile screen
- ✅ User avatars smaller (50px)
- ✅ Text sizes adjust
- ✅ Progress bars still visible
- ✅ Voting still works
- ✅ Touch-friendly click targets

---

## 🐛 Troubleshooting

### **Problem: Vote doesn't register**

**Check:**
1. Backend is running: `curl http://localhost:8080/Surveys/ShowAllSurveys`
2. Console for errors (F12 → Console tab)
3. Network tab for failed requests (F12 → Network tab)

**Solution:**
- Restart backend if needed
- Check CORS settings
- Verify survey IDs are correct

---

### **Problem: Progress bars don't show**

**Check:**
1. Browser console for CSS errors
2. Inspect element to verify classes applied
3. Check if percentages are numbers (not null)

**Solution:**
- Clear browser cache (Ctrl+Shift+Delete)
- Hard refresh (Ctrl+F5)

---

### **Problem: "✓ Voted" badge doesn't appear**

**Check:**
1. Console logs for vote confirmation
2. Verify `isAnswerVoted()` method is called
3. Check `userVotes` Map in component

**Solution:**
- Check TypeScript for errors
- Verify survey IDs match
- Restart frontend dev server

---

## 📊 Expected Data Flow

```
User Clicks Answer
       ↓
Frontend: voteOnAnswer(survey, answer)
       ↓
Service: PUT /Surveys/ShowAllSurveys
       ↓
Backend: Increment vote count
       ↓
Backend: Recalculate percentages
       ↓
Backend: Save to database
       ↓
Backend: Return updated answers
       ↓
Frontend: Update survey.answer
       ↓
Frontend: Add to userVotes Map
       ↓
UI: Show "✓ Voted" badge
       ↓
UI: Update progress bars
       ↓
UI: Show new percentages
```

---

## 🎯 Success Criteria

### **Visual:**
- ✅ Dark gray progress bars for all answers
- ✅ Purple gradient progress bar for voted answer
- ✅ "✓ Voted" badge visible
- ✅ Purple border on voted answer
- ✅ Vote counts displayed
- ✅ Percentages add up to 100%

### **Functional:**
- ✅ One-click voting works
- ✅ Vote count increases in database
- ✅ Percentages recalculate correctly
- ✅ Can vote multiple times
- ✅ Can change vote
- ✅ No duplicate votes while processing

### **Performance:**
- ✅ Vote registers within 1 second
- ✅ Smooth animations (no lag)
- ✅ No console errors
- ✅ No network errors

---

## 📝 Test Checklist

- [ ] Backend running on port 8080
- [ ] Frontend running on port 4200
- [ ] Can view all surveys
- [ ] User info displays correctly
- [ ] Progress bars are dark gray initially
- [ ] Can click on answers
- [ ] Vote registers in backend
- [ ] "✓ Voted" badge appears
- [ ] Progress bar turns purple
- [ ] Vote count increases
- [ ] Percentages recalculate
- [ ] Can change vote
- [ ] Can vote on multiple surveys
- [ ] Mobile responsive works
- [ ] Animations are smooth
- [ ] No console errors

---

## 🎉 Demo Script

**For showing off the feature:**

1. **Open "Show All Surveys" page**
   - "Here we can see all surveys from different users"
   - "Each survey shows the user's profile and username"

2. **Point to progress bars**
   - "Notice the dark gray horizontal progress bars"
   - "They show the current voting percentages"

3. **Hover over an answer**
   - "When I hover, the answer becomes clickable"
   - "The border turns purple"

4. **Click to vote**
   - "One click and my vote is recorded"
   - "Watch the progress bar grow and turn purple"
   - "The '✓ Voted' badge appears"
   - "Vote count increases"
   - "All percentages recalculate automatically"

5. **Change vote**
   - "I can change my vote by clicking another answer"
   - "The previous vote styling disappears"
   - "The new answer gets highlighted"

6. **Show multiple surveys**
   - "I can vote on multiple surveys"
   - "Each one tracks my vote independently"

---

**Happy Testing! 🎉**