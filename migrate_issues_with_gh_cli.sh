#!/bin/bash
# GitHub CLI Issue Migration Script
# Generated automatically to migrate issues with 'Test case' label
# From: UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC
# To: UA-4700-taqc/greencitytaqc4700

set -e  # Exit on error

echo "=========================================="
echo "Issue Migration Script"
echo "From: UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC"
echo "To: UA-4700-taqc/greencitytaqc4700"
echo "=========================================="
echo ""

# Check if gh CLI is installed
if ! command -v gh &> /dev/null; then
    echo "Error: GitHub CLI (gh) is not installed"
    echo "Please install it from: https://cli.github.com/"
    exit 1
fi

# Check if authenticated
if ! gh auth status &> /dev/null; then
    echo "Error: Not authenticated with GitHub CLI"
    echo "Please run: gh auth login"
    exit 1
fi

# Target repository
TARGET_REPO="UA-4700-taqc/greencitytaqc4700"
SOURCE_REPO="UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC"

# Issue numbers to migrate
ISSUES=(168 133 132 131 130 129 127 126 125 124 123 119 118 117 116 115 112 111 110 109 107 105 96 95 94 93 41 40 39 38 37 36 35 34 33 19 18 17 16 15 14 13)

CREATED_COUNT=0
FAILED_COUNT=0
TOTAL=${#ISSUES[@]}

echo "Found $TOTAL issues to migrate"
echo ""

for ISSUE_NUM in "${ISSUES[@]}"; do
    echo "[$((CREATED_COUNT + FAILED_COUNT + 1))/$TOTAL] Processing issue #$ISSUE_NUM..."
    
    # Fetch issue data from source
    if ! ISSUE_DATA=$(gh issue view $ISSUE_NUM --repo "$SOURCE_REPO" --json title,body,labels,state,number,url,createdAt,author 2>/dev/null); then
        echo "  ✗ Failed to fetch issue #$ISSUE_NUM"
        ((FAILED_COUNT++))
        continue
    fi
    
    # Extract issue fields
    TITLE=$(echo "$ISSUE_DATA" | jq -r '.title')
    BODY=$(echo "$ISSUE_DATA" | jq -r '.body // ""')
    STATE=$(echo "$ISSUE_DATA" | jq -r '.state')
    URL=$(echo "$ISSUE_DATA" | jq -r '.url')
    CREATED_AT=$(echo "$ISSUE_DATA" | jq -r '.createdAt')
    AUTHOR=$(echo "$ISSUE_DATA" | jq -r '.author.login')
    LABELS=$(echo "$ISSUE_DATA" | jq -r '.labels[].name' | tr '\n' ',' | sed 's/,$//')
    
    # Create migration note
    MIGRATION_NOTE="> **Note:** This issue was migrated from [$SOURCE_REPO#$ISSUE_NUM]($URL)
> - **Original Author:** @$AUTHOR
> - **Original Created:** $CREATED_AT
> - **Original State:** $STATE

---

"
    
    # Combine migration note with original body
    NEW_BODY="$MIGRATION_NOTE$BODY"
    
    # Create temp file for body
    TEMP_BODY=$(mktemp)
    echo "$NEW_BODY" > "$TEMP_BODY"
    
    # Create issue in target repo
    if [ -n "$LABELS" ]; then
        if NEW_ISSUE=$(gh issue create --repo "$TARGET_REPO" --title "$TITLE" --body-file "$TEMP_BODY" --label "$LABELS" 2>&1); then
            echo "  ✓ Created: $NEW_ISSUE"
            ((CREATED_COUNT++))
        else
            echo "  ✗ Failed to create issue: $NEW_ISSUE"
            ((FAILED_COUNT++))
        fi
    else
        if NEW_ISSUE=$(gh issue create --repo "$TARGET_REPO" --title "$TITLE" --body-file "$TEMP_BODY" 2>&1); then
            echo "  ✓ Created: $NEW_ISSUE"
            ((CREATED_COUNT++))
        else
            echo "  ✗ Failed to create issue: $NEW_ISSUE"
            ((FAILED_COUNT++))
        fi
    fi
    
    # Clean up temp file
    rm "$TEMP_BODY"
    
    # Small delay to avoid rate limiting
    sleep 1
done

echo ""
echo "=========================================="
echo "Migration Summary:"
echo "  Total issues: $TOTAL"
echo "  Successfully created: $CREATED_COUNT"
echo "  Failed: $FAILED_COUNT"
echo "=========================================="
