# Project Ordinate  
_A lightweight system for turning raw thoughts into structured, professional documents._

Ordinate is a three-tier web application that transforms unstructured text or short voice recordings into clean, well-structured documents. Unlike basic transcription tools or generic summarizers, Ordinate focuses on **organization**—mapping raw content into predefined templates, detecting missing information, and ensuring users stay fully in control through a human-in-the-loop editing flow.

Ordinate demonstrates a blend of **NLP engineering**, **browser-based speech recognition**, and **full-stack system design**, offering a private and efficient workflow for converting free-form thinking into actionable knowledge.

---

## Key Features
- **Structured Document Generation**  
  Converts raw text or voice input into organized documents (e.g., meeting notes, lecture notes, clinical SOAP templates).

- **In-Browser Speech-to-Text**  
  Whisper.cpp compiled to WebAssembly enables **private, offline transcription** with no external API calls.

- **Lightweight NLP Pipeline**  
  Cleans and organizes content (filler removal, punctuation restoration, sentence splitting, template mapping).

- **Missing / Unclear Information Detection**  
  Flags gaps like “No action items detected.”

- **Human-in-the-Loop Editing**  
  Users can revise the transcript, adjust sections, and regenerate structure before exporting.

- **PDF Export**  
  Finalized documents can be exported as polished PDFs.

---

## Why I Built This
Professionals constantly produce unstructured information—brain dumps, meeting notes, voice memos, problem-solving sessions—but transforming that into clean documentation is slow and error-prone.

Ordinate aims to bridge that gap by offering:
- private transcription  
- structured templates  
- clarity checks  
- a clean workflow from **thought → structure → documentation**

This project reflects my interests in **Full-Stack Software Engineering** and **Machine Learning**. 

---

## NLP Pipeline Overview
Ordinate uses a lightweight pipeline built for simplicity and reliability:

### **Cleanup Stage**
- Remove filler words (“um,” “like,” “you know”)  
- Restore punctuation and sentence boundaries  

### **Organization Stage**
- Map sentences to template sections using rules and heuristic keyword matching  

### **Validation Stage**
- Confidence indicators for clarity  
- Missing/unclear section detection  

This avoids hallucinations and ensures output remains grounded in user-provided content.

---

## Voice Mode (Private, Offline Speech Recognition)
Ordinate integrates **Whisper.cpp** in the browser through WebAssembly:

- No third-party API calls  
- All audio stays local  
- Ideal for short summaries and meeting notes  

This enables strong privacy with low latency.

---

## System Architecture

### **Frontend**
- JavaScript + React  
- MediaRecorder API for audio capture  
- WebAssembly bindings for Whisper.cpp  

### **Backend**
- Java + Spring Boot  
- REST API for text processing, organization, and confidence scoring  
- Optional audio ingestion endpoint  

### **Database**
- PostgreSQL for persistence of generated documents and templates  

### **Exporting**
- Apache PDFBox for PDF generation  

---

## Project Status
Actively in development.  
MVP includes:
- 3 templates  
- Text mode  
- Whisper.cpp voice mode  
- Missing-info detection  
- PDF export  

Future versions will expand into template editing, real-time validation, analytics, search, and mobile clients.

---

## Documentation
Detailed documents are located in the [`docs/`](./docs) folder:
- One Pager  
- Architecture  
- Workflow  
- Phases  
- Non-Goals  
- Future Enhancements  

---

## Contributions
This is a personal project, but feedback and suggestions are welcome.  
