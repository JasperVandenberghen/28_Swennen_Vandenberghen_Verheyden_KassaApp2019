git add -A
@echo off
set /p message="Enter commit message: "
git commit -m "%message%"
git push