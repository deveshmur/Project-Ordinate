import { useRef, useState } from "react";
import { uploadAudio } from "./api/ordinateApi";

export default function Voice() {
  const mediaRecorderRef = useRef(null);
  const chunksRef = useRef([]);

  const [recording, setRecording] = useState(false);
  const [audioUrl, setAudioUrl] = useState(null);
  const [audioFile, setAudioFile] = useState(null);
  const [error, setError] = useState(null);
  const [uploading, setUploading] = useState(false);
  const [uploadResult, setUploadResult] = useState(null);

    async function startRecording() {
    setError(null);
    setAudioUrl(null);
    setAudioFile(null);
    setUploadResult(null);
    setUploading(false);

    try {
      const stream = await navigator.mediaDevices.getUserMedia({ audio: true });

      const mr = new MediaRecorder(stream);
      mediaRecorderRef.current = mr;
      chunksRef.current = [];

      mr.ondataavailable = (e) => {
        if (e.data && e.data.size > 0) chunksRef.current.push(e.data);
      };

      mr.onstop = () => {
        stream.getTracks().forEach((t) => t.stop());

        const blob = new Blob(chunksRef.current, { type: mr.mimeType });
        const url = URL.createObjectURL(blob);

        const ext = mr.mimeType.includes("webm") ? "webm" : "wav";
        const file = new File([blob], `recording.${ext}`, { type: mr.mimeType });

        setAudioFile(file);
        setAudioUrl(url);
      };

      mr.start();
      setRecording(true);
    } catch (e) {
      setError(
        e?.message ||
          "Failed to access microphone. Check browser permissions."
      );
    }
  }

  function stopRecording() {
    const mr = mediaRecorderRef.current;
    if (mr && mr.state !== "inactive") {
      mr.stop();
    }
    setRecording(false);
  }

    async function handleUpload() {
    if (!audioFile) return;

    setError(null);
    setUploading(true);
    setUploadResult(null);

    try {
      const data = await uploadAudio(audioFile);
      setUploadResult(data);
    } catch (e) {
      setError(e.message);
    } finally {
      setUploading(false);
    }
  }

  return (
    <div style={{ padding: "1rem", maxWidth: "800px", margin: "0 auto" }}>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <div style={{ display: "flex", gap: "0.75rem", marginBottom: "1rem" }}>
        <button onClick={startRecording} disabled={recording}>
          Start Recording
        </button>
        <button onClick={stopRecording} disabled={!recording}>
          Stop
        </button>
      </div>

      <button onClick={handleUpload} disabled={!audioFile || uploading}>
        {uploading ? "Uploading..." : "Upload to Backend"}
      </button>

      {recording && <p>Recording…</p>}

      {audioUrl && (
        <>
          <h2>Playback</h2>
          <audio controls src={audioUrl} />

          <h3>Debug Info</h3>
          {uploadResult && (
          <>
            <h3>Backend Response</h3>
            <pre style={{ background: "#f4f4f4", color: "#111", padding: "1rem" }}>
              {JSON.stringify(uploadResult, null, 2)}
            </pre>
          </>
        )}
          <pre style={{ background: "#f4f4f4", color: "#111", padding: "1rem" }}>
            {
            JSON.stringify(
              {
                name: audioFile?.name,
                mimeType: audioFile?.type,
                sizeBytes: audioFile?.size,
              },
              null,
              2
            )
          }
          </pre>
        </>
      )}
    </div>
  );
}
