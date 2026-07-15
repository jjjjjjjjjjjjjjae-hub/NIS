#!/bin/bash
TOKEN=$(cat token.txt)
git remote set-url origin https://$TOKEN@github.com/jjjjjjjjjjjjjjae-hub/NIS.git
git add .
git commit -m "Update system overlay code and fix structures"
git push origin main
echo "Код сәтті жіберілді, бро!"
