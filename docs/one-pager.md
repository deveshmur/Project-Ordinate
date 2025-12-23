# Project Ordinate

## Summary
Project Ordinate is a web application that converts unstructured text or short voice recordings into clean, professionally structured documents. Users can paste rough notes or record audio directly in the browser, and the system organizes the content into clear sections with missing- or unclear-information flags and an editable output. Once finalized, the user can export the document as a PDF. It’s a lightweight, private, human-in-the-loop tool for turning raw thoughts into actionable documentation.

## Solution
People constantly produce unstructured information—brain dumps, meeting notes, voice memos, engineering ideas—but turning that into clean, shareable documentation is slow and painful. Existing tools either only transcribe or only summarize, but none help users organize information into professional structures and identify what is missing. Project Ordinate solves this by providing lightweight, private, structured note-building with minimal effort.

## Technologies & Tools
- **Frontend:** JavaScript, React, MediaRecorder API  
- **Speech-to-Text:** Whisper.cpp, WebAssembly (WASM)  
- **Backend:** Java, Spring Boot (Spring MVC, Spring Data JPA)  
- **NLP Pipeline:** Java Regular Expressions (Pattern/Matcher), custom sentence tokenizer, keyword/heuristic section-mapping engine  
- **File Handling:** Spring Boot Multipart Resolver, Java NIO file APIs, WASM bindings for Whisper.cpp  
- **Database:** PostgreSQL  
- **Exporting:** Apache PDFBox  
- **Build:** Maven  

## User Workflow

### Step 1: Choose an Industry & Template Type
- Software Engineer – Team Meeting  
- College Student – Lecture Notes  
- Healthcare – Clinical SOAP Note  

### Step 2: Choose an Input Mode
**Text Mode**  
- Plain text editor  
- Users can type unstructured notes, brainstorms, bullet lists, or raw thoughts  

**Voice Mode**  
- Record directly in the browser  
- Whisper.cpp (compiled to WebAssembly) performs private, offline transcription (max recording length TBD)

### Step 3: Process Information into a Document

#### Stage 1 — Cleanup
- Remove filler words (e.g., “um,” “like,” “you know”)  
- Restore punctuation + sentence boundaries  
- Highlight which parts of the raw text contribute to each section  

#### Stage 2 — Organization
- Map content into designated template sections using rules and keyword matching  

#### Stage 3 — Validation
- Display confidence indicators (heuristic, sentence-level clarity score)  
- Flag missing or unclear sections  
  - Example: “No action items detected.”  

### Step 4: Edit
- Edit any field  
- Adjust or reorder sections  
- Regenerate organization  

### Step 5: Export
- Export and download the final document as a PDF  

## Phases

### Sprint 1 — Foundations
- UI skeleton  
- Text mode input → basic organization  
- Wrap Whisper.cpp as a microservice  
- Basic NLP pipeline (punctuation + filler removal)  

### Sprint 2 — Templates & Structure
- Implement 3 templates  
- Missing-section detector  
- Confidence scoring  
- Source highlighting  

### Sprint 3 — Voice Mode
- Integrate Whisper.cpp (browser-based)  
- Transcription pipeline  
- Transcript review UI  

### Sprint 4 — Export & Polish
- PDF export integration  
- Styling and UX improvements  

## Non-Goals (v1)
- No converting text into non-document formats  
- No voice-activated “real-time coaching”  
- No image processing  
- No hallucination-based rewriting or content generation  
- No collaboration, shared workspaces, or accounts  
- No template builder (templates are fixed in v1)  
- No analytics, search, tagging, or document history  
- No exporting to formats other than PDF  
- No mobile app  

## Future Enhancements
- Real-time voice/typing cross-check for completeness  
- Template builder (custom document types)  
- Speaker diarization for multi-person meetings  
- Search, tags, versioning, history, analytics  
- Mobile app (native iOS, SwiftUI)  
- Team spaces and shared documents  
