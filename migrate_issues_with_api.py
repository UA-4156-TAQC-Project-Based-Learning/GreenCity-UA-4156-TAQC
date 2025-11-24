#!/usr/bin/env python3
"""
GitHub API Issue Migration Script
Migrates issues from UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC 
to UA-4700-taqc/greencitytaqc4700
"""

import os
import sys
import requests
import time
from typing import Dict, Optional

# Configuration
SOURCE_REPO = "UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC"
TARGET_REPO = "UA-4700-taqc/greencitytaqc4700"
GITHUB_API = "https://api.github.com"

# Get GitHub token
GITHUB_TOKEN = os.environ.get("GITHUB_TOKEN")
if not GITHUB_TOKEN:
    print("Error: GITHUB_TOKEN environment variable not set")
    print("Please set it with: export GITHUB_TOKEN=your_token_here")
    sys.exit(1)

HEADERS = {
    "Authorization": f"token {GITHUB_TOKEN}",
    "Accept": "application/vnd.github.v3+json"
}

# Issues to migrate
ISSUE_NUMBERS = [168, 133, 132, 131, 130, 129, 127, 126, 125, 124, 123, 119, 118, 117, 116, 115, 112, 111, 110, 109, 107, 105, 96, 95, 94, 93, 41, 40, 39, 38, 37, 36, 35, 34, 33, 19, 18, 17, 16, 15, 14, 13]


def fetch_issue(repo: str, issue_number: int) -> Optional[Dict]:
    """Fetch a single issue from repository"""
    url = f"{GITHUB_API}/repos/{repo}/issues/{issue_number}"
    response = requests.get(url, headers=HEADERS)
    
    if response.status_code == 200:
        return response.json()
    else:
        print(f"  Error fetching issue #{issue_number}: {response.status_code}")
        return None


def create_issue(repo: str, issue_data: Dict) -> Optional[Dict]:
    """Create issue in target repository"""
    url = f"{GITHUB_API}/repos/{repo}/issues"
    
    # Prepare migration note
    original_url = issue_data.get("html_url", "")
    original_number = issue_data.get("number", "")
    original_author = issue_data.get("user", {}).get("login", "unknown")
    original_created = issue_data.get("created_at", "")
    original_state = issue_data.get("state", "open")
    
    migration_note = f"""
> **Note:** This issue was migrated from [{SOURCE_REPO}#{original_number}]({original_url})
> - **Original Author:** @{original_author}
> - **Original Created:** {original_created}
> - **Original State:** {original_state}

---

"""
    
    body = migration_note + (issue_data.get("body") or "")
    label_names = [label["name"] for label in issue_data.get("labels", [])]
    
    payload = {
        "title": issue_data.get("title", ""),
        "body": body,
        "labels": label_names
    }
    
    response = requests.post(url, headers=HEADERS, json=payload)
    
    if response.status_code == 201:
        return response.json()
    else:
        print(f"  Error creating issue: {response.status_code}")
        print(f"  Response: {response.text}")
        return None


def main():
    """Main migration function"""
    print("=" * 80)
    print(f"Migrating {len(ISSUE_NUMBERS)} issues")
    print(f"From: {SOURCE_REPO}")
    print(f"To: {TARGET_REPO}")
    print("=" * 80)
    print()
    
    created_count = 0
    failed_count = 0
    total = len(ISSUE_NUMBERS)
    
    for idx, issue_num in enumerate(ISSUE_NUMBERS, 1):
        print(f"[{idx}/{total}] Processing issue #{issue_num}...")
        
        # Fetch from source
        issue_data = fetch_issue(SOURCE_REPO, issue_num)
        if not issue_data:
            failed_count += 1
            continue
        
        # Create in target
        new_issue = create_issue(TARGET_REPO, issue_data)
        if new_issue:
            print(f"  ✓ Created: {new_issue['html_url']}")
            created_count += 1
        else:
            failed_count += 1
        
        # Rate limiting delay
        time.sleep(1)
    
    print()
    print("=" * 80)
    print("Migration Summary:")
    print(f"  Total: {total}")
    print(f"  Created: {created_count}")
    print(f"  Failed: {failed_count}")
    print("=" * 80)


if __name__ == "__main__":
    main()
