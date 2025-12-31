# Data Model

## TEMPLATE
Represents a predefined document template such as “Team Meeting,” “Lecture Notes,” or “SOAP Note.”

### Fields
- `template_id` (PK): UUID
- `name`: string
- `description`: string
- `industry`: string

### Relationships
- One Template → many Template_Sections
- One Template → many Documents

---

## TEMPLATE_SECTION
Represents a required section within a template (e.g., Decisions, Action Items, Subjective, Objective).

### Fields
- `section_id` (PK): UUID
- `template_id` (FK to Template): UUID
- `name`: string
- `order_index`: integer

### Relationships
- One Template → many Template_Sections
- One Template_Section → many Document_Sections

---

## DOCUMENT
Represents a single document created by the user after processing the input.

### Fields
- `document_id` (PK): UUID
- `template_id` (FK to Template): UUID
- `title`: string
- `created_at`: timestamp
- `updated_at`: timestamp
- `export_format`: string ("pdf" or "md")

### Relationships
- One Document → many Document_Inputs
- One Document → many Document_Sections
- Each Document → belongs to one Template

---

## DOCUMENT_INPUT
Stores whatever raw content the user provided (typed text or transcribed voice).

### Fields
- `input_id` (PK): UUID
- `document_id` (FK to Document): UUID
- `input_type`: string ("text" or "voice")
- `raw_text`: text
- `cleaned_text`: text
- `recording_length_seconds`: integer (voice mode only)
- `created_at`: timestamp

### Relationships
- One Document → many Document_Inputs

---

## DOCUMENT_SECTION
Represents a single organized section of the final output.

### Fields
- `doc_section_id` (PK): UUID
- `document_id` (FK to Document): UUID
- `template_section_id` (FK to Template_Section): UUID
- `content`: text
- `confidence_score`: decimal
- `missing_flag`: boolean
- `source_highlights`: text (JSON)

### Relationships
- One Document → many Document_Sections
- Each Document_Section → corresponds to one Template_Section

---

## Relationship Summary
TEMPLATE (1) → (many) TEMPLATE_SECTION
TEMPLATE (1) → (many) DOCUMENT
DOCUMENT (1) → (many) DOCUMENT_INPUT
DOCUMENT (1) → (many) DOCUMENT_SECTION
TEMPLATE_SECTION (1) → (many) DOCUMENT_SECTION