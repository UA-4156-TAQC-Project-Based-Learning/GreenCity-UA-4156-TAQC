# Issue Migration Summary

## Task Completion Status

✅ **Migration scripts and documentation have been created successfully**

This PR provides all the necessary tools to migrate **42 issues** with the "Test case" label from the source repository to the target repository.

## What Has Been Completed

### 1. Issue Discovery
- Identified all 42 issues in `UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC` with label "Test case"
- Issue numbers: #168, #133, #132, #131, #130, #129, #127, #126, #125, #124, #123, #119, #118, #117, #116, #115, #112, #111, #110, #109, #107, #105, #96, #95, #94, #93, #41, #40, #39, #38, #37, #36, #35, #34, #33, #19, #18, #17, #16, #15, #14, #13

### 2. Migration Scripts Created

#### Option 1: GitHub CLI Script (Recommended)
**File:** `migrate_issues_with_gh_cli.sh`

**Features:**
- Automatically fetches each issue from source repository
- Creates corresponding issues in target repository  
- Preserves issue metadata (title, body, labels, state)
- Adds migration note with original issue link and metadata
- Includes error handling and progress reporting
- No API token required (uses gh authentication)

#### Option 2: Python API Script
**File:** `migrate_issues_with_api.py`

**Features:**
- Direct GitHub API integration
- Same functionality as CLI script
- Requires GITHUB_TOKEN environment variable
- Good for automation/CI environments

### 3. Documentation
**File:** `MIGRATION_README.md`

**Contents:**
- Step-by-step installation instructions for both methods
- Authentication setup guide
- Troubleshooting section
- Complete list of all issues to migrate with links
- Manual migration alternative

## How to Execute the Migration

### Quick Start (Recommended Method)

```bash
# 1. Install GitHub CLI
# macOS: brew install gh
# Windows: winget install GitHub.cli
# Linux: sudo apt install gh

# 2. Authenticate
gh auth login

# 3. Run migration
./migrate_issues_with_gh_cli.sh
```

### Alternative Method (API)

```bash
# 1. Create GitHub token at: https://github.com/settings/tokens
# Select scope: repo (all)

# 2. Set token
export GITHUB_TOKEN=your_token_here

# 3. Install requirements
pip install requests

# 4. Run migration
python3 migrate_issues_with_api.py
```

## Migration Details

Each migrated issue will include:
- ✅ Original issue title
- ✅ Original issue body/description  
- ✅ All labels (if they exist in target repo)
- ✅ Migration metadata note:
  - Link to original issue
  - Original author
  - Original creation date
  - Original state (open/closed)

## What Users Need to Do

1. **Review the scripts** - Check `migrate_issues_with_gh_cli.sh` and `migrate_issues_with_api.py`
2. **Choose migration method** - GitHub CLI is recommended for ease of use
3. **Follow MIGRATION_README.md** - Complete instructions are provided
4. **Execute the migration** - Run the appropriate script
5. **Verify results** - Check that all 42 issues were created in `UA-4700-taqc/greencitytaqc4700`

## Important Notes

⚠️ **Permissions Required:**
- You must have **write access** to the target repository `UA-4700-taqc/greencitytaqc4700`
- GitHub CLI or API token must have proper permissions

⚠️ **What Won't Be Migrated:**
- Issue numbers (new issues will get new numbers in target repo)
- Original assignees (would need to be re-assigned manually)
- Original comments (only the issue body is migrated)
- Reactions and timestamps (except as noted in migration metadata)

⚠️ **Original Issues:**
- Source issues remain unchanged
- No issues are deleted or modified in the source repository

## Validation

Both scripts have been validated:
- ✅ Bash script syntax checked
- ✅ Python script syntax checked
- ✅ All 42 issues confirmed in source repository
- ✅ Scripts include error handling and reporting
- ✅ Rate limiting delays included to avoid API limits

## Support

For detailed instructions and troubleshooting, see: **MIGRATION_README.md**

For any issues during migration:
1. Check error messages carefully
2. Verify authentication (gh auth status or GITHUB_TOKEN)  
3. Confirm write access to target repository
4. Check GitHub API status if encountering issues

## Files in This PR

```
MIGRATION_README.md              - Complete documentation with instructions
migrate_issues_with_gh_cli.sh    - GitHub CLI migration script (recommended)
migrate_issues_with_api.py       - Python API migration script (alternative)
SUMMARY.md                       - This file
```

---

**Ready to migrate!** Follow the instructions in MIGRATION_README.md to complete the issue migration.
