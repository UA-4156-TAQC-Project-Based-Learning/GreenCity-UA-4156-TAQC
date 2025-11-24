# Example of a Migrated Issue

This is what an issue will look like after migration to the target repository:

---

## Title
[Create News][Test Case] Title Field Validation

## Body

> **Note:** This issue was migrated from [UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC#14](https://github.com/UA-4156-TAQC-Project-Based-Learning/GreenCity-UA-4156-TAQC/issues/14)
> - **Original Author:** @OlhenShu
> - **Original Created:** 2025-05-26T10:53:41Z
> - **Original State:** closed

---

**Description:** Verify the validation of the "Title" field (mandatory, maximum 170 characters) and that the "Publish" button remains disabled until both Title and Main Text (Content) fields are filled and at least one tag is choosed.

**Preconditions:**

- The user is logged into the system.
- The "Create News" form is open.

**Steps:**

1. Navigate to [GreenCity News](https://www.greencity.cx.ua/#/greenCity/news) and click "Create News".
2. Leave the "Title" field empty.
3. Verify that the Title field's border is highlighted in red.
4. Verify that the "Publish" button is disabled.
5. Check that the character counter shows "0/170".
6. Enter a 171-character-long string into the "Title" field (e.g., "A" * 171).
7. Verify that the text is truncated to 170 characters and that the counter is highlighted in red when exceeding the limit.
8. Enter a valid title, such as "Test News" (9 characters).
9. Verify that the counter displays "9/170" and that the border is not red.
10. Verify that the "Publish" button is still disabled because the "Main Text" field is empty.
11. Select one of the following tags: "News", "Events", "Education", "Initiatives", "Ads".
12. Enter valid text into the "Main Text" field.
13. Verify that the "Publish" button becomes enabled only after both the Title and Main Text fields are filled and at least one tag is selected.

**Expected Result:**

- The "Title" field is highlighted in red if left empty.
- The character counter is highlighted in red when exceeding 170 characters.
- Input is limited to 170 characters.
- The field appears normal with a valid title.
- The "Publish" button remains disabled until both "Title" and "Main Text" are filled.

**Automated Verification:**

- Check the border style of the "Title" field when empty and when exceeding the limit.
- Validate the character counter value and its styling.
- Ensure the "Publish" button is disabled until both required fields are filled, and becomes enabled only after valid input is provided.

## Labels
- Create news
- Test case
- sprint 1
- UI
- pri: high

---

**Key Points:**
1. ✅ Migration note clearly indicates this was copied from another repository
2. ✅ Original author credit is preserved
3. ✅ Original creation date and state are documented
4. ✅ Link back to original issue for reference
5. ✅ All content and labels are preserved
6. ✅ New issue number will be assigned by target repository
