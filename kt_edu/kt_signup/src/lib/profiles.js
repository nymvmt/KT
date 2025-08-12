import { supabase } from './supabase'

// 프로필 생성
export async function createProfile(userId, username, intro) {
  try {
    const { data, error } = await supabase
      .from('profiles')
      .insert([
        {
          user_id: userId,  // UUID 타입의 사용자 ID
          username,
          intro
        }
      ])
      .select()

    if (error) throw error
    return { data, error: null }
  } catch (error) {
    return { data: null, error: error.message }
  }
}

// 프로필 조회 (단일)
export async function getProfile(userId) {
  try {
    const { data, error } = await supabase
      .from('profiles')
      .select('*')
      .eq('user_id', userId)
      .single()

    if (error) throw error
    return { data, error: null }
  } catch (error) {
    return { data: null, error: error.message }
  }
}

// 모든 프로필 조회 (게시판용)
export async function getAllProfiles() {
  try {
    const { data, error } = await supabase
      .from('profiles')
      .select('*')
      .order('created_at', { ascending: false })

    if (error) throw error
    return { data, error: null }
  } catch (error) {
    return { data: null, error: error.message }
  }
}

// 프로필 업데이트
export async function updateProfile(profileId, updates) {
  try {
    const { data, error } = await supabase
      .from('profiles')
      .update(updates)
      .eq('id', profileId)
      .select()

    if (error) throw error
    return { data, error: null }
  } catch (error) {
    return { data: null, error: error.message }
  }
}

// 프로필 삭제
export async function deleteProfile(profileId) {
  try {
    const { error } = await supabase
      .from('profiles')
      .delete()
      .eq('id', profileId)

    if (error) throw error
    return { error: null }
  } catch (error) {
    return { error: error.message }
  }
}

// 사용자 ID로 프로필 존재 여부 확인
export async function checkProfileExists(userId) {
  try {
    const { data, error } = await supabase
      .from('profiles')
      .select('id')
      .eq('user_id', userId)
      .single()

    if (error && error.code === 'PGRST116') {
      // 프로필이 존재하지 않는 경우
      return { exists: false, error: null }
    }
    
    if (error) throw error
    return { exists: true, error: null }
  } catch (error) {
    return { exists: false, error: error.message }
  }
}

// 사용자 ID로 프로필 조회 (user_id 기준)
export async function getProfileByUserId(userId) {
  try {
    const { data, error } = await supabase
      .from('profiles')
      .select('*')
      .eq('user_id', userId)
      .single()

    if (error && error.code === 'PGRST116') {
      return { data: null, error: null }
    }
    
    if (error) throw error
    return { data, error: null }
  } catch (error) {
    return { data: null, error: error.message }
  }
} 