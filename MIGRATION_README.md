# Issue Migration Guide

This directory contains scripts to migrate **42 issues** with the "Test case" label from:
- **Source:** UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC
- **Target:** UA-4700-taqc/greencitytaqc4700

## Migration Options

### Option 1: GitHub CLI (Recommended) ⭐

**Advantages:**
- No need to create API tokens
- Easier authentication
- Better error handling
- Automatically handles rate limiting

**Steps:**

1. **Install GitHub CLI:**
   ```bash
   # macOS
   brew install gh
   
   # Windows
   winget install GitHub.cli
   
   # Linux (Debian/Ubuntu)
   sudo apt install gh
   
   # Or download from: https://cli.github.com/
   ```

2. **Authenticate:**
   ```bash
   gh auth login
   ```
   Follow the prompts to authenticate with your GitHub account.

3. **Run the migration script:**
   ```bash
   ./migrate_issues_with_gh_cli.sh
   ```

### Option 2: GitHub API (Python)

**Requirements:**
- Python 3.6+
- `requests` library

**Steps:**

1. **Create a GitHub Personal Access Token:**
   - Go to: https://github.com/settings/tokens
   - Click "Generate new token (classic)"
   - Give it a descriptive name (e.g., "Issue Migration")
   - Select scopes: **`repo`** (all repository permissions)
   - Generate and copy the token

2. **Set the token as environment variable:**
   ```bash
   export GITHUB_TOKEN=ghp_your_token_here
   ```

3. **Install Python requirements:**
   ```bash
   pip install requests
   ```

4. **Run the migration script:**
   ```bash
   python3 migrate_issues_with_api.py
   ```

## Issue List (42 issues)

The following issues will be migrated:
#168, #133, #132, #131, #130, #129, #127, #126, #125, #124, #123, #119, #118, #117, #116, #115, #112, #111, #110, #109, #107, #105, #96, #95, #94, #93, #41, #40, #39, #38, #37, #36, #35, #34, #33, #19, #18, #17, #16, #15, #14, #13

## Migration Details

Each migrated issue will:
- ✓ Include a note at the top indicating it was migrated from the source repository
- ✓ Preserve original issue metadata (author, creation date, state) in the migration note
- ✓ Copy all labels from the source issue (if they exist in the target repository)
- ✓ Maintain the original issue body/description
- ✓ Include a link back to the original issue

## Notes

- The migration process includes rate limiting delays to avoid hitting GitHub API limits
- Issues are created in the target repository as new issues (numbers will be different)
- The original issues in the source repository are not modified or deleted
- If an issue fails to migrate, the script will continue with the next one and report the failure at the end

## Manual Alternative

If you prefer to migrate issues manually or need to migrate specific issues:

1. Open each source issue: https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/{number}
2. Copy the title and body
3. Create a new issue in the target repository: https://github.com/UA-4700-taqc/greencitytaqc4700/issues/new
4. Paste the content and add appropriate labels
5. Add a note mentioning it was migrated from the source repository

### Source Issue Links:

- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/168
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/133
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/132
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/131
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/130
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/129
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/127
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/126
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/125
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/124
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/123
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/119
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/118
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/117
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/116
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/115
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/112
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/111
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/110
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/109
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/107
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/105
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/96
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/95
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/94
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/93
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/41
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/40
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/39
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/38
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/37
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/36
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/35
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/34
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/33
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/19
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/18
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/17
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/16
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/15
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/14
- https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/13

## Troubleshooting

### GitHub CLI Authentication Issues
```bash
# Re-authenticate
gh auth logout
gh auth login
```

### API Token Permissions
Make sure your GitHub Personal Access Token has the `repo` scope (all repository permissions).

### Rate Limiting
If you encounter rate limiting errors, the scripts include delays. For the API script, you can increase the delay in the `time.sleep()` call.

### Permission Errors
Ensure you have write access to the target repository: UA-4700-taqc/greencitytaqc4700

## Support

If you encounter issues:
1. Check the error messages carefully
2. Verify your authentication (gh auth status or GITHUB_TOKEN)
3. Confirm you have write access to the target repository
4. Check GitHub's API status: https://www.githubstatus.com/
