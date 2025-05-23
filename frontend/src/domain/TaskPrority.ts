export enum TaskPrority {
  HIGH = "HIGH",
  MEDIUM = "MEDIUM",
  LOW = "LOW",
}

// Type guard for TaskPriority
// eslint-disable-next-line @typescript-eslint/no-explicit-any
export function isTaskPrority(value: any): value is TaskPriority {
  return Object.values(TaskPriority).includes(value);
}
