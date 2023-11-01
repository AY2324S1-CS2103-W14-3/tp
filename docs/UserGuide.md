---

layout: page
title: User Guide
---

**ConText** is a **desktop app designed for managing contacts of NUS SoC students.** ConText leverages a Command Line Interface (CLI) combined with a modern Graphical User Interface (GUI) to offer speedy contact management. If you're familiar with typing commands, you'll find ConText incredibly efficient.
![ConText UI](images/Ui.png)

* Table of Contents
  {:toc}

---

## Getting Started

Upon launching ConText, you'll be presented with a clean interface. At the top is the command input box. Right below it, feedback from the commands you input will be displayed. Beneath the feedback section, you'll find a list of your contacts.

### Entering text commands

1. Click on the command input box at the top of the application.
2. Type your command.
3. Press `Enter` to run the command.

Any feedback, including errors, will appear directly below the input box.

---

### Adding a Contact: `add`

//TODO UG needs quite a lot of updates. We should take from or re-edit off the original AB3 documentation to make ours more fleshed out. We should also make ours match the messages in the code.

Easily add a new contact to your list with the `add` command.

**Format:**
`add n/FULL_NAME p/PHONE_NUMBER e/EMAIL [o/NOTE] [t/TAG]... [a/ALTERNATE_CONTACT]...`

* A contact can have 0 or 1 note
* A contact can have any number of tags (including 0)
* A contact can have any number of alternate contacts (including 0)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The format for alternate contact is "Type of alternate contact": "Name of alternate contact"
</div>

**Examples:**
- `add n/John Doe p/98765432 e/john.doe@email.com`
- `add n/John Doe p/98765432 e/john.doe@email.com o/CS2103 Prof. t/NUS t/CS2103 course a/Telegram: JohnDoe`

---

### Viewing All Contacts: `list`

Display all your stored contacts.

**Format:**
`list`

---

### Deleting Contacts: `delete`

Removes one or more contacts based on their indices. The indices refer to the index numbers shown in the displayed contact list.

**Format:**
`delete INDEX [INDEX]...`

- You can delete multiple contacts at once by specifying multiple indices separated by spaces.
- Indices **must be positive integers** like 1, 2, 3, …​
- Duplicated indices are only count once.
- Invalid indices will cause abortion of the delete command.

**Examples:**
- `delete 1`
  Deletes the contact at index 1.

- `delete 1 3 5`
  Deletes the contacts at the specified indices: 1, 3, and 5.

---

### Viewing Help: `help`

Click the help button on the top to navigate to a comprehensive help manual.

---

### Locating Contacts by Name: `find`

Search and display contacts with names containing any of the given keywords.

**Format:**
`find [KEYWORD]...`

**Example:**
`find John Alice`

---

### Clearing All Entries: `clear`

Remove all contacts from your list. Apply with caution!

**Format:**
`clear`

---

### Exiting the Program: `exit`

Close the ConText application.

**Format:**
`exit`

---

### Editing a Contact: `edit`

Modify an existing contact's details in your list.

**Format:**
`edit INDEX [n/FULL_NAME] [p/PHONE_NUMBER] [e/EMAIL] [o/NOTE] [t/TAGS]... [a/ALTERNATE_CONTACT]...`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. 
The index must be a positive integer 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, existing tags will be removed. The adding of tags is not cumulative.
* Similar to tags, existing alternate contacts will be removed when editing them.
* You can remove all the contact's tags by typing `t/` without specifying any tags after it.
* Similar to tags, you can remove all the contact's alternate contacts by typing `a/` without specifying any alternate
contacts after it.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The format for alternate contact is "Type of alternate contact": "Name of alternate contact"
</div>

**Example:**
`edit 2 n/John Doe p/98765432 e/john.doe@email.com`

---

Remember, whenever in doubt, you can always navigate to the help webpage via the `help` tab at the top.
