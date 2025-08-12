export default function SkillsCard({ 
  title, 
  skills, 
  icon, 
  className = ''
}) {
  return (
    <div className={`bg-gray-800/50 backdrop-blur-md rounded-2xl p-8 border border-gray-700/50 ${className}`}>
      <h3 className="text-2xl font-bold mb-6 text-white flex items-center">
        <span className="text-3xl mr-3">{icon}</span>
        {title}
      </h3>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {skills.map((skill, index) => (
          <div key={index} className="flex items-center p-4 bg-gray-700/30 rounded-lg">
            <div className={`w-12 h-12 bg-gradient-to-r ${skill.color} rounded-lg flex items-center justify-center mr-4`}>
              <span className="text-white text-xl">{skill.icon}</span>
            </div>
            <div>
              <p className="text-white font-medium">{skill.name}</p>
              <p className="text-gray-400 text-sm">{skill.description}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
} 